package com.ltse;

import java.sql.Timestamp;
import java.util.StringJoiner;

public class Trade {

	private final Timestamp timestamp;
	private final String broker;
	private final Long sequenceId;
	private final String type;
	private final String symbol;
	private final Long quantity;
	private final Double price;
	private final String side;
	private boolean acceptedTrade;
	private StringJoiner reasonCode = new StringJoiner(";");

	public Trade(final Builder builder) {
		this.timestamp = builder.timestamp;
		this.broker = builder.broker;
		this.sequenceId = builder.sequenceId;
		this.type = builder.type;
		this.symbol = builder.symbol;
		this.quantity = builder.quantity;
		this.price = builder.price;
		this.side = builder.side;
		this.acceptedTrade = builder.acceptedTrade;
		this.reasonCode = builder.reasonCode;
	}
	
	public String getBroker() {
		return broker;
	}

	public long getSequenceId() {
		return sequenceId;
	}

	public String getType() {
		return type;
	}

	public String getSymbol() {
		return symbol;
	}

	public long getQuantity() {
		return quantity;
	}

	public double getPrice() {
		return price;
	}

	public String getSide() {
		return side;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}
	
	public boolean isAcceptedTrade() {
		return acceptedTrade;
	}
	
	public String getReasonCode() {
		return reasonCode.toString();
	}

	public void acceptedTrade(final boolean acceptedTrade) {
		this.acceptedTrade = acceptedTrade;
	}

	public void reasonCode(final String reasonCode) {
		this.reasonCode.add(reasonCode);
	}
	
	public String resultString() {
		return "Trade [broker=" + broker + ", sequenceId=" + sequenceId + (acceptedTrade ? "" : ", reasonCode=" + getReasonCode()) + "]";		
	}
	
	@Override
	public String toString() {
		return "Trade [timestamp=" + timestamp.toString() + ", broker=" + broker + ", sequenceId=" + sequenceId + ", type=" + type
				+ ", symbol=" + symbol + ", quantity=" + quantity + ", price=" + price + ", side=" + side
				+ ", acceptedTrade=" + acceptedTrade + ", reasonCode=" + getReasonCode() + "]";
	}
	
	
	public static class Builder {
		private Timestamp timestamp;
		private String broker;
		private Long sequenceId;
		private String type;
		private String symbol;
		private Long quantity;
		private Double price;
		private String side;
		private boolean acceptedTrade = true;
		private StringJoiner reasonCode = new StringJoiner(";");

		public Builder timestamp(final Timestamp ts) {
			this.timestamp = ts;
			return this;
		}
		
		public Builder broker(final String broker) {
			this.broker = broker;
			return this;
		}

		public Builder sequenceId(final Long sequenceId) {
			this.sequenceId = sequenceId;
			return this;
		}

		public Builder type(final String type) {
			this.type = type;
			return this;
		}

		public Builder symbol(final String symbol) {
			this.symbol = symbol;
			return this;
		}

		public Builder quantity(final Long quantity) {
			this.quantity = quantity;
			return this;
		}

		public Builder price(final Double price) {
			this.price = price;
			return this;
		}
		
		public Builder side(final String side) {
			this.side = side;
			return this;
		}
		
		public Builder isAcceptedTrade(final boolean acceptedTrade) {
			this.acceptedTrade = acceptedTrade;
			return this;
		}

		public Builder reasonCode(final String reasonCode) {
			this.reasonCode.add(reasonCode);
			return this;
		}

		public Trade build() {
			return new Trade(this);
		}
		
	}
}
