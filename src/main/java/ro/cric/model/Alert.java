package ro.cric.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ALERTS")
public class Alert {

	@Id
	@Column(name = "ALERT_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long alertId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ORGANIZATION_ID")
	private Organization organization;

	@Column(name = "SENT_AT")
	private String sentAt;

	@Column(name = "URGENGY")
	private String urgency;

	@Column(name = "SEVERITY")
	private String severity;

	@Column(name = "CATEGORY")
	private String category;

	public Alert() {

	}

	public long getAlertId() {
		return alertId;
	}

	public void setAlertId(long alertId) {
		this.alertId = alertId;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public String getSentAt() {
		return sentAt;
	}

	public void setSentAt(String sentAt) {
		this.sentAt = sentAt;
	}

	public String getUrgency() {
		return urgency;
	}

	public void setUrgency(String urgency) {
		this.urgency = urgency;
	}

	public String getSeverity() {
		return severity;
	}

	public void setSeverity(String severity) {
		this.severity = severity;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

}
