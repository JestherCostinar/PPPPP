package org.rampup.util;

public class ApiResponse<T> {
	private boolean success;
    private T results;

    public ApiResponse(boolean success, T results) {
        this.success = success;
        this.results = results;
    }

	/**
	 * @return the success
	 */
	public boolean isSuccess() {
		return success;
	}

	/**
	 * @param success the success to set
	 */
	public void setSuccess(boolean success) {
		this.success = success;
	}

	/**
	 * @return the results
	 */
	public T getResults() {
		return results;
	}

	/**
	 * @param results the results to set
	 */
	public void setResults(T results) {
		this.results = results;
	}
    
    
}
