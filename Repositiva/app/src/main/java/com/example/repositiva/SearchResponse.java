package com.example.repositiva;

public class SearchResponse {
    private Slip[] slips;

    public Slip[] getSlips() {
        return slips;
    }

    public void setSlips(Slip[] slips) {
        this.slips = slips;
    }

    public class Slip {
        private String advice;

        public String getAdvice() {
            return advice;
        }

        public void setAdvice(String advice) {
            this.advice = advice;
        }
    }
}