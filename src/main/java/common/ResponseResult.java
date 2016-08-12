package common;

public class ResponseResult{
	private boolean success;
	private Object data;
	private String message;
	private int total;
	private String status;
	

	public static ResponseResult success(String message){
		ResponseResult result=new ResponseResult();
		result.setSuccess(true);
		result.setMessage(message);
		return result;
	}
	
	public static ResponseResult success(Object data){
		ResponseResult result=new ResponseResult();
		result.setSuccess(true);
		result.setData(data);
		return result;
	}

	
	public static ResponseResult success(Object data,int total){
		ResponseResult result=new ResponseResult();
		result.setSuccess(true);
		result.setData(data);
		result.setTotal(total);
		return result;
	}
	
	public static ResponseResult success(String message,Object data){
		ResponseResult result=new ResponseResult();
		result.setSuccess(true);
		result.setMessage(message);
		result.setData(data);
		return result;
	}
	
	public static ResponseResult success(String message,Object data,int total){
		ResponseResult result=new ResponseResult();
		result.setSuccess(true);
		result.setMessage(message);
		result.setData(data);
		result.setTotal(total);
		return result;
	}
	
	public static ResponseResult failure(String message){
		ResponseResult result=new ResponseResult();
		result.setSuccess(false);
		result.setMessage(message);
		return result;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}
