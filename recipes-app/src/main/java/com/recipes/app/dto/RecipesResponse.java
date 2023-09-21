package com.recipes.app.dto;

import java.util.List;

public class RecipesResponse {

    private List<Results> Results;

    public List<com.recipes.app.dto.Results> getResults() {
        return Results;
    }

    public void setResults(List<com.recipes.app.dto.Results> results) {
        Results = results;
    }
}
