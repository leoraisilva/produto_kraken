package br.com.kraken.Produto.java.model;

public enum Rules {
    ADMIN("admin"),
    USER("user");

    private String rules;
    Rules (String rules) {
        this.rules = rules;
    }

}
