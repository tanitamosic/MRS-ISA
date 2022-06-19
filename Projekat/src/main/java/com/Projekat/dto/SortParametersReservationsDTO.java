package com.Projekat.dto;

public class SortParametersReservationsDTO {

    private Boolean sortByDateAscending;
    private Boolean sortByDateDescending;
    private Boolean sortByPriceAscending;
    private Boolean sortByPrisceDescending;

    public SortParametersReservationsDTO() {}

    public Boolean getSortByDateAscending() {
        return sortByDateAscending;
    }

    public void setSortByDateAscending(Boolean sortByDateAscending) {
        this.sortByDateAscending = sortByDateAscending;
    }

    public Boolean getSortByDateDescending() {
        return sortByDateDescending;
    }

    public void setSortByDateDescending(Boolean sortByDateDescending) {
        this.sortByDateDescending = sortByDateDescending;
    }

    public Boolean getSortByPriceAscending() {
        return sortByPriceAscending;
    }

    public void setSortByPriceAscending(Boolean sortByPriceAscending) {
        this.sortByPriceAscending = sortByPriceAscending;
    }

    public Boolean getSortByPrisceDescending() {
        return sortByPrisceDescending;
    }

    public void setSortByPrisceDescending(Boolean sortByPrisceDescending) {
        this.sortByPrisceDescending = sortByPrisceDescending;
    }
}
