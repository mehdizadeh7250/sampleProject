package com.example.baloot.alimehdizadeh.presentation.viewModel

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.baloot.alimehdizadeh.domain.model.remote.GetEveryThingFromQuery
import com.example.baloot.alimehdizadeh.domain.useCase.GetEveryThingDataUseCase
import com.example.baloot.alimehdizadeh.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class EveryThingFragmentViewModel @Inject constructor(
    private val getEveryThingDataUseCase: GetEveryThingDataUseCase
) : BaseViewModel() {
    private val paginator = Channel<Int>()
    private val everyThingDataLiveData = MutableLiveData<GetEveryThingFromQuery>()
    val everyThingData: LiveData<GetEveryThingFromQuery>
        get() = everyThingDataLiveData


    init {
        getData()
        nextContactUsPage(1)
    }

    fun getData() {
        scope.launch {
            paginator.receiveAsFlow().collect { page ->
//                if (page < 6) {
                getEveryThingDataUseCase.getEveryThingQuery(
                    query = "apple",
                    sortBy = "popularity",
                    apiKey = "0aad477b155a4607b0f7a68bdd6e5f4c",
                    page = page,
                    onResult = { flow ->
                        scope.launch {
                            flow.catch {
                                errorLiveData.postValue(this.toString())
                            }.collect { response ->
                                val result = handleResponse(response)
                                if (result != null) {
                                    everyThingDataLiveData.postValue(result!!)
                                }
                            }
                        }
                    },
                    onException = {
                        errorLiveData.postValue(it)
                    }
                )
//                } else
//                    Toast.makeText()
            }
        }
    }

    fun nextContactUsPage(page: Int) {
        scope.launch { paginator.send(page) }
    }
}