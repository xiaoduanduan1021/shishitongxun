package util.page;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;

public class PageList
  implements Serializable
{
  ArrayList datalist = new ArrayList();
  ArrayList flaglist = new ArrayList();
  private int startOfCurPage = 0;
  private int viewStartOfCurPage = 0;
  private int pageSize = 10;
  private int totalCount = 0;
  private int pageCount = 10;
  private boolean hasNextPage = false;
  private boolean hasPreviousPage = false;
  private int curPage = 0;
  private int nextPage = 0;
  private int previousPage = 0;
  private int endOfCurPage = 0;
  private int startOfNextPage = 0;
  private int startOfPreviousPage = 0;
  private int startOfLastPage = 0;
  private int totalPages = 0;

  private Map<String, Object> fujiaZhi;//附加返回值
  
  
  
  public Map<String, Object> getFujiaZhi() {
	return fujiaZhi;
}

public void setFujiaZhi(Map<String, Object> fujiaZhi) {
	this.fujiaZhi = fujiaZhi;
}

public PageList(ArrayList datalist, int startOfCurPage, int pageSize, int totalCount)
  {
    this.datalist = datalist;
    this.startOfCurPage = startOfCurPage;
    this.viewStartOfCurPage = (startOfCurPage + 1);
    this.pageSize = pageSize;
    this.totalCount = totalCount;

    this.pageCount = getPageCount();
    this.hasNextPage = isHasNextPage();
    this.hasPreviousPage = isHasPreviousPage();
    this.curPage = getCurPage();
    this.nextPage = getNextPage();
    this.previousPage = getPreviousPage();
    this.endOfCurPage = getEndOfCurPage();
    this.startOfNextPage = getStartOfNextPage();
    this.startOfPreviousPage = getStartOfPreviousPage();
    this.startOfLastPage = getStartOfLastPage();
    this.totalPages = getTotalPages();
  }

  public ArrayList getDatalist()
  {
    return this.datalist;
  }

  public void setDatalist(ArrayList datalist) {
    this.datalist = datalist;
  }

  public int getPageCount()
  {
    return this.datalist.size();
  }

  public boolean isHasNextPage()
  {
    return (this.startOfCurPage + this.pageSize < this.totalCount);
  }

  public boolean isHasPreviousPage()
  {
    return (this.startOfCurPage > 0);
  }

  public int getCurPage()
  {
    if (getTotalCount() == 0) {
      return 0;
    }
    return (getStartOfCurPage() / getPageSize() + 1);
  }

  public int getNextPage()
  {
    return (getCurPage() + 1);
  }

  public int getPreviousPage()
  {
    if (getCurPage() == 0) {
      return 0;
    }
    return (getCurPage() - 1);
  }

  public int getStartOfCurPage()
  {
    return this.startOfCurPage;
  }

  public int getViewStartOfCurPage()
  {
    if (getTotalCount() == 0) {
      return 0;
    }

    return (this.startOfCurPage + 1);
  }

  public int getEndOfCurPage()
  {
    return (this.startOfCurPage + getPageCount());
  }

  public int getStartOfNextPage()
  {
    return (this.startOfCurPage + this.pageSize);
  }

  public int getStartOfPreviousPage()
  {
    return Math.max(this.startOfCurPage - this.pageSize, 0);
  }

  public int getStartOfLastPage()
  {
    if (this.totalCount % getPageSize() == 0) {
      return (this.totalCount - getPageSize());
    }

    return (this.totalCount - (this.totalCount % getPageSize()));
  }

  public int getTotalCount()
  {
    return this.totalCount;
  }

  public void setTotalCount(int totalCount)
  {
    this.totalCount = totalCount;
  }

  public int getTotalPages()
  {
    if (getTotalCount() % getPageSize() == 0) {
      return (getTotalCount() / getPageSize());
    }
    return (getTotalCount() / getPageSize() + 1);
  }

  public int getPageSize()
  {
    return this.pageSize;
  }

  public void setPageSize(int pageSize)
  {
    this.pageSize = pageSize;
  }

  public int getStartCount(int page) {
    int startcount = 0;
    startcount = (page - 1) * this.pageSize;
    return startcount;
  }

  public String getPageOption()
  {
    String sData = "";
    if (getTotalCount() > 0) {
      for (int i = 1; i <= this.totalPages; ++i) {
        if (i == getCurPage()) {
          sData = sData + "<option value='" + getStartCount(i) + "' selected>第 " + i + "页 </option>";
        }
        else {
          sData = sData + "<option value='" + getStartCount(i) + "'>第 " + i + "页 </option>";
        }
      }
    }
    return sData;
  }

  public String getPageText()
  {
    String sData = "";
    if (getTotalCount() > 0) {
      sData = "第 <font color='red'>" + getCurPage() + "</font> 页  | 共 <font color='red'>" + this.totalPages + "</font> 页 转到 <input type='text' style='height:20px; width:25px;' maxlength='5' name='gopage' value=''><input type='hidden' name='page_size' value='" + this.pageSize + "'><input type='hidden' name='totalPages' value='" + this.totalPages + "'></span></td>";
    }
    return sData;
  }
}

