package com.userInteractionsAndAndroidResourse.dogsapp.Presenter.Presenter.BreedListPresenter;

public class PresenterBreedList implements IPresenterListBreed {

    public PresenterBreedList(IViewListBreed viewBreeds) {
        this.viewBreeds = viewBreeds;
    }

    IViewListBreed viewBreeds;


    @Override
    public void loadBreedList() {

    }

    @Override
    public void loadImagesBreed(String dogBreed) {

    }
}
