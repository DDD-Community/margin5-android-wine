package com.margin.wine.sign.step.one

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.margin.wine.core.arch.MviViewModel
import com.margin.wine.sign.SignContract
import java.util.regex.Pattern

class SignStepOneViewModel :
    MviViewModel<SignStepOneContract.Event, SignStepOneContract.State, SignStepOneContract.Effect>() {

    private val _editTextLiveData = MutableLiveData<String>()
    val editTextLiveData: LiveData<String> get() = _editTextLiveData

    fun setEditText(str: String) {
        if (_editTextLiveData.value != str) {
            _editTextLiveData.value = str
        }
    }

    override fun createInitialState(): SignStepOneContract.State {
        return SignStepOneContract.State(SignStepOneContract.SignDataState.Init(false))
    }

    override fun handleEvent(event: SignStepOneContract.Event) {
        when (event) {
            is SignStepOneContract.Event.OnChangedInputText -> handleInputText(event.str)
        }
    }

    private fun String.isMatching(): Boolean {
         return !Pattern.matches("^[ㄱ-ㅎ가-힣]*$", this) && Pattern.matches("^[a-zA-Z0-9]*$", this) && count() > 1
    }

    private fun handleInputText(text: String) {
        if (text.isMatching()) {
            setState {
                copy(
                    signDataState = SignStepOneContract.SignDataState.InputText(
                        text = text,
                        guideText = "사용 가능한 이름입니다.",
                        isClearInputText = true,
                        isClickableBottomButton = true
                    )
                )
            }
        } else {
            setState {
                copy(
                    signDataState = SignStepOneContract.SignDataState.InputText(
                        text = text,
                        guideText = "2자 이상 10자 이내 영문, 숫자 입력 가능합니다.",
                        isClearInputText = false,
                        isClickableBottomButton = false
                    )
                )
            }
        }
    }

    /*private fun getMainData() = viewModelScope.launch {
        setState { copy(mainDataState = MainContract.MainDataState.Loading) }
        delay(2000L)

        val count = (fetchHomeWineListUseCase.invoke().getOrNull() ?: listOf()).count()
        setState { copy(mainDataState = MainContract.MainDataState.Success(count)) }
    }*/
}