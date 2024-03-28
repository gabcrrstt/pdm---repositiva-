package com.example.repositiva;

import java.util.List;

public class Conselho {
        private int id;
            private String textoConselho;

        public Conselho(List<Conselho> conselhosList) {}

        public Conselho(int id, String textoConselho) {
            this.id = id;
            this.textoConselho = textoConselho;
        }

    public Conselho() {

    }

    public String getTextoConselho() {
                return textoConselho;
            }

            public void setTextoConselho(String textoConselho) {
                this.textoConselho = textoConselho;
            }




        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }


    }


