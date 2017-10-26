package com.flyingstudio.fscore.ui.recycler;

/**
 * Created by guopu on 2017/10/20.
 */

public class PageIndicator {
    private int totoalPages;
    private int currentPage;
    public int startLoadNextPage(){
        return ++currentPage;
    }

    public int getTotoalPages() {
        return totoalPages;
    }

    public PageIndicator setTotoalPages(int totoalPages) {
        this.totoalPages = totoalPages;
        return this;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public PageIndicator setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
        return this;
    }
}
