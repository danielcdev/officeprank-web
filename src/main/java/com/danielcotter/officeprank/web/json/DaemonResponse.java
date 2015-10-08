package com.danielcotter.officeprank.web.json;

public class DaemonResponse {
	
	Boolean armed;

	/**
	 * @param armed
	 */
	public DaemonResponse(Boolean armed) {
		this.armed = armed;
	}

	/**
	 * @return the armed
	 */
	public Boolean getArmed() {
		return armed;
	}

	/**
	 * @param armed
	 *            the armed to set
	 */
	public void setArmed(Boolean armed) {
		this.armed = armed;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((armed == null) ? 0 : armed.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof DaemonResponse))
			return false;
		DaemonResponse other = (DaemonResponse) obj;
		if (armed == null) {
			if (other.armed != null)
				return false;
		} else if (!armed.equals(other.armed))
			return false;
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "DaemonResponse [armed=" + armed + "]";
	}
}
