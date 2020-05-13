package com.ltse;

public class RawTrade {

	private final String timestamp;
	private final String broker;
	private final String sequenceId;
	private final String type;
	private final String symbol;
	private final String quantity;
	private final String price;
	private final String side;

	public RawTrade(final Builder builder) {
		this.timestamp = builder.timestamp;
		this.broker = builder.broker;
		this.sequenceId = builder.sequenceId;
		this.type = builder.type;
		this.symbol = builder.symbol;
		this.quantity = builder.quantity;
		this.price = builder.price;
		this.side = builder.side;
	}
	
	public String getBroker() {
		return broker;
	}

	public String getSequenceId() {
		return sequenceId;
	}

	public String getType() {
		return type;
	}

	public String getSymbol() {
		return symbol;
	}

	public String getQuantity() {
		return quantity;
	}

	public String getPrice() {
		return price;
	}

	public String getSide() {
		return side;
	}

	public String getTimestamp() {
		return timestamp;
	}
	
	@Override
	public String toString() {
		return "RawTrade [timestamp=" + timestamp + ", broker=" + broker + ", sequenceId=" + sequenceId + ", type="
				+ type + ", symbol=" + symbol + ", quantity=" + quantity + ", price=" + price + ", side=" + side + "]";
	}
	
	public static class Builder {
		private String timestamp;
		private String broker;
		private String sequenceId;
		private String type;
		private String symbol;
		private String quantity;
		private String price;
		private String side;

		public Builder timestamp(final String ts) {
			this.timestamp = ts;
			return this;
		}
		
		public Builder broker(final String broker) {
			this.broker = broker;
			return this;
		}

		public Builder sequenceId(final String sequenceId) {
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

		public Builder quantity(final String quantity) {
			this.quantity = quantity;
			return this;
		}

		public Builder price(final String price) {
			this.price = price;
			return this;
		}
		
		public Builder side(final String side) {
			this.side = side;
			return this;
		}
		
		public RawTrade build() {
			return new RawTrade(this);
		}
	}

}
