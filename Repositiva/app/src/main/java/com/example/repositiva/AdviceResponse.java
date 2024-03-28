package com.example.repositiva;

public class AdviceResponse {
     private Slip slip;

        public Slip getSlip() {
            return slip;
        }

        public void setSlip(Slip slip) {
            this.slip = slip;
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
