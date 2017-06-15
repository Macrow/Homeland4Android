package org.mstudio.homeland4android.ui.base;

/**
 * author : Macrow
 * e-mail : Macrow_wh@163.com
 * time   : 2017/06/04
 * desc   :
 */
public interface BaseView<T> {

    void setPresenter(T presenter);

    void finishActivity();

    void showLoading();

    void showError();

    void hideFeedbackBox();

    void showMessage(String msg);

}
