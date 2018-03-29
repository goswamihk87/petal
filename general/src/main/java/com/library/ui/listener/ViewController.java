package com.library.ui.listener;

/**
 * Created by agile-01 on 6/21/2017.
 * <p>
 * In typical mvc, Activities/Fragments often work as ViewController.
 * The term has been taken from ios ViewControllers where they usually mean controlling view actions outside of view.
 * For those who are familiar with mvp, this just a MVP view. you can extend it and treat like a base view.
 * This interface should be implemented by activity/fragment and will be used to trigger view actions from outside components.
 */
public interface ViewController {

    void showLoader();

    void hideLoader();

    void showAlert(String msg);

    void showError(String msg);

}
