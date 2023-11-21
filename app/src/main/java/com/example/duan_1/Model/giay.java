package com.example.duan_1.Model;

public class giay {
        private String tenGiay;
        private String loaiGiay;
        private int giaTien;
        private String avatagiay;

        public giay(String tenGiay, String loaiGiay, int giaTien, String avatagiay) {
            this.tenGiay = tenGiay;
            this.loaiGiay = loaiGiay;
            this.giaTien = giaTien;
            this.avatagiay = avatagiay;
        }

        public String getTenGiay() {
            return tenGiay;
        }

        public String getLoaiGiay() {
            return loaiGiay;
        }

        public int getGiaTien() {
            return giaTien;
        }

        public String getAvatagiay() {
            return avatagiay;
        }
    }


