package saadow.office_management_app.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "ORDERS", schema = "MA_STUDENT")
public class Order implements java.io.Serializable {

	@Id
	@Column(name = "ORDER_NUM")
	private BigDecimal orderNum;
	@Temporal(TemporalType.DATE)
	@Column(name = "ORDER_DATE")
	private Date orderDate;
	@Column
	private String mfr;
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PRODUCT")
	private Product product;
	@Column
	private BigDecimal qty;
	@Column
	private BigDecimal amount;
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CUST")
	private Customer customer;
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "REP")
	private Salesreps salesreps;

	public Order() {
	}

	public Order(BigDecimal orderNum) {
		this.orderNum = orderNum;
	}

	public Order(BigDecimal orderNum, Product product) {
		this.orderNum = orderNum;
		this.product = product;
	}

	public Order(BigDecimal orderNum, Product product, Date date, String mfr, BigDecimal qty, BigDecimal amount) {
		this.orderNum = orderNum;
		this.product = product;
		this.orderDate = date;
		this.mfr = mfr;
		this.qty = qty;
		this.amount = amount;
	}

	public BigDecimal getOrderNum() {
		return this.orderNum;
	}

	public void setOrderNum(BigDecimal orderNum) {
		this.orderNum = orderNum;
	}

	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Customer getCustomers() {
		return this.customer;
	}

	public Salesreps getSalesreps() {
		return this.salesreps;
	}

	public Date getOrderDate() {
		return this.orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public String getMfr() {
		return this.mfr;
	}

	public void setMfr(String mfr) {
		this.mfr = mfr;
	}

	public BigDecimal getQty() {
		return this.qty;
	}

	public void setQty(BigDecimal qty) {
		this.qty = qty;
	}

	public BigDecimal getAmount() {
		return this.amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((amount == null) ? 0 : amount.hashCode());
		result = prime * result + ((mfr == null) ? 0 : mfr.hashCode());
		result = prime * result + ((orderDate == null) ? 0 : orderDate.hashCode());
		result = prime * result + ((orderNum == null) ? 0 : orderNum.hashCode());
		result = prime * result + ((qty == null) ? 0 : qty.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		if (amount == null) {
			if (other.amount != null)
				return false;
		} else if (!amount.equals(other.amount))
			return false;
		if (mfr == null) {
			if (other.mfr != null)
				return false;
		} else if (!mfr.equals(other.mfr))
			return false;
		if (orderDate == null) {
			if (other.orderDate != null)
				return false;
		} else if (!orderDate.equals(other.orderDate))
			return false;
		if (orderNum == null) {
			if (other.orderNum != null)
				return false;
		} else if (!orderNum.equals(other.orderNum))
			return false;
		if (qty == null) {
			if (other.qty != null)
				return false;
		} else if (!qty.equals(other.qty))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Order [orderNum=" + orderNum + ", orderDate=" + orderDate + ", mfr=" + mfr + ", qty=" + qty
				+ ", amount=" + amount + "]";
	}

}
