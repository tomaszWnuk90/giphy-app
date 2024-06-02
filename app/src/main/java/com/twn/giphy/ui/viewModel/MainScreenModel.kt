package com.twn.giphy.ui.viewModel

import androidx.lifecycle.ViewModel

import androidx.lifecycle.viewModelScope
import com.twn.giphy.extensions.toGifItem
import com.twn.giphy.model.GifItem
import com.twn.network.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

import javax.inject.Inject

@OptIn(FlowPreview::class)
@HiltViewModel
class MainScreenModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private val _viewState =
        MutableStateFlow<ViewState<List<GifItem>>>(value = ViewState.Loading)

    val viewState = _viewState.asStateFlow()

    private val _userTextInputState = MutableStateFlow(value = "")

    init {
        viewModelScope.launch {
            _userTextInputState.debounce(timeoutMillis = 500)
                .collectLatest { input ->
                    processText(input)
                }
        }
    }

    private fun loadDataOnLaunch() {
        viewModelScope.launch {
            repository.downloadGifs().onSuccess {
                onSuccess(it?.map {
                    it.toGifItem()
                })
            }.onFailure {
                onError()
            }
        }
    }

    private fun processText(text: String) {
        _viewState.update {
            ViewState.Loading
        }
        if (text.isNotEmpty()) {
            searchForGif(text)
        } else {
            loadDataOnLaunch()
        }
    }

    private fun searchForGif(text: String) {
        viewModelScope.launch {
            repository.searchForGifs(text).onSuccess {
                onSuccess(it?.map {
                    it.toGifItem()
                })
            }.onFailure {
                onError()
            }
        }
    }

    private fun onSuccess(result: List<GifItem>?) {
        _viewState.update {
            result?.let { gifs ->
                ViewState.Content(gifs)
            } ?: run {
                ViewState.NoContent
            }
        }
    }

    private fun onError() {
        _viewState.update {
            ViewState.Error
        }
    }

    fun textEntered(text: String) {
        _userTextInputState.update { text }
    }
}