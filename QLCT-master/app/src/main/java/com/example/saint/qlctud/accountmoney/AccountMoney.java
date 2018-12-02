package com.example.saint.qlctud.accountmoney;

import com.example.saint.qlctud.amount.Amount;

import java.math.BigDecimal;

public class AccountMoney {
	public BigDecimal amountOfAccount;
	public String nameOfAccount;
	public String TypeMoney;
	public String Description;
	public int Icon;
	public AccountMoney(BigDecimal amountOfAccount, String nameOfAccount, String typeMoney, String description, int icon) {
		this.amountOfAccount = amountOfAccount;
		this.nameOfAccount = nameOfAccount;
		TypeMoney = typeMoney;
		Description = description;
		Icon = icon;
	}

	public AccountMoney(BigDecimal amountOfAccount, String nameOfAccount, String typeMoney) {
		this.amountOfAccount = amountOfAccount;
		this.nameOfAccount = nameOfAccount;
		TypeMoney = typeMoney;
	}

	public int getIcon() {
		return Icon;
	}

	public void setIcon(int icon) {
		Icon = icon;
	}

	public BigDecimal getAmountOfAccount() {
		return amountOfAccount;
	}

	public void setAmountOfAccount(BigDecimal amountOfAccount) {
		this.amountOfAccount = amountOfAccount;
	}

	public String getNameOfAccount() {
		return nameOfAccount;
	}

	public void setNameOfAccount(String nameOfAccount) {
		this.nameOfAccount = nameOfAccount;
	}

	public String getTypeMoney() {
		return TypeMoney;
	}

	public void setTypeMoney(String typeMoney) {
		TypeMoney = typeMoney;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public AccountMoney() {
	}

	public AccountMoney(BigDecimal amountOfAccount, String nameOfAccount, String typeMoney, String description) {
		this.amountOfAccount = amountOfAccount;
		this.nameOfAccount = nameOfAccount;
		TypeMoney = typeMoney;
		Description = description;
	}

}