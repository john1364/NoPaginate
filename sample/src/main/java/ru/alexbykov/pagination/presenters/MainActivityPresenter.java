package ru.alexbykov.pagination.presenters;

import android.util.Log;

import ru.alexbykov.nopaginate.callback.OnLoadMoreListener;
import ru.alexbykov.pagination.utils.MockUtils;
import ru.alexbykov.pagination.views.IMainActivityView;

/**
 * Created by Alex Bykov on 11.08.2017.
 * You can contact me at: me@alexbykov.ru.
 */

public class MainActivityPresenter implements OnLoadMoreListener {

    private IMainActivityView view;

    public MainActivityPresenter(IMainActivityView view) {
        this.view = view;
        addItems();
    }

    private void addItems() {
        view.addItems(MockUtils.getMockItems());
    }


    @SuppressWarnings({"SameParameterValue", "unused"})
    private void getItems(int limit, int offset) {
        view.showPaginateError(false);
        view.showPaginateLoading(true);

        MockUtils.mockHttpRequest(new MockUtils.NetworkCallback() {
            @Override
            public void onSuccess() {
                view.addItems(MockUtils.getMockItems());
                view.showPaginateLoading(false);
            }

            @Override
            public void onError() {
                view.showPaginateLoading(false);
                view.showPaginateError(true);
            }
        });
    }

    @Override
    public void onLoadMore() {
        Log.d("OnLoadMore", "onLoadMore: ");
        getItems(1, 2);
    }

}

