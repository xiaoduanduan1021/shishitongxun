package util.page;

public class PageDto {
	
	private String createStartDate;//搜索的创建时间的开始时间
	
	private String createEndDate; //搜索的创建时间结束时间
	
	private String closeStartDate;//搜索的截止日期的开始时间
	
	private String closeEndDate; //搜索的截止日期结束时间	
	
	private String startTime;
	
	private String endTime;
	
	private int startCount = 0;//分页查询的开始页
	
	private int pageSize = 10;//分页查询每页的大小
	
	private String order = "desc";//升序还是降序排列
	
	private String sort = "createTime";//按照那个字段排序
	
	private int pageNumber = 1;//设置开始页

	private String searchName; // 搜索关键字
	
	private int expand1;//扩展字段1
	private int expand2;//扩展字段2

	public String getCreateStartDate() {
		return createStartDate;
	}

	public void setCreateStartDate(String createStartDate) {
		this.createStartDate = createStartDate;
	}

	public String getCreateEndDate() {
		return createEndDate;
	}

	public void setCreateEndDate(String createEndDate) {
		this.createEndDate = createEndDate;
	}

	public String getCloseStartDate() {
		return closeStartDate;
	}

	public void setCloseStartDate(String closeStartDate) {
		this.closeStartDate = closeStartDate;
	}

	public String getCloseEndDate() {
		return closeEndDate;
	}

	public void setCloseEndDate(String closeEndDate) {
		this.closeEndDate = closeEndDate;
	}

	public int getStartCount() {
		return startCount;
	}

	public void setStartCount(int startCount) {
		this.startCount = startCount;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public String getSearchName() {
		return searchName;
	}

	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public int getExpand1() {
		return expand1;
	}

	public void setExpand1(int expand1) {
		this.expand1 = expand1;
	}

	public int getExpand2() {
		return expand2;
	}

	public void setExpand2(int expand2) {
		this.expand2 = expand2;
	}
	
	
}
