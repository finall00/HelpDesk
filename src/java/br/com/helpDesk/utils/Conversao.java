package br.com.helpDesk.utils;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Conversao {

    public static Date converterData(String data) throws ParseException {
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        if (data == null || data.trim().equals("")) {
            return null;
        } else {
            Date date = fmt.parse(data);
            return date;
        }
    }

    public static String data2String(Date data) {
        SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
        String dataF = fmt.format(data);
        return dataF;

    }

    public static Date dataAtual() {
        SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
        Date nData = new Date(System.currentTimeMillis());
        return nData;
    }

    public static Double valorDinheiro(String valor) {
        String conv = valor.substring(2, valor.length());
        conv = conv.replaceAll("[./-]", "");
        conv = conv.replace(",", ".").trim();
        return Double.parseDouble(conv);

    }

    public static String valorDinheiro(double valor, String pais) {
        NumberFormat form = null;

        if (pais.equals("BR")) {
            form = NumberFormat.getCurrencyInstance();
        } else if (pais.equals("US")) {
            form = NumberFormat.getInstance(new Locale("en", "US"));
        }
        String money = form.format(valor);
        return money; 
    }
}
