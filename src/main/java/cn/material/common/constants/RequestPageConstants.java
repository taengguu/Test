package cn.material.common.constants;

/**
 * 请求分页的key常量
 * 
 * @author Mr.wang
 *
 */
public interface RequestPageConstants {
	

    /**
     * 请求时，代表每页记录数的key
     */
    public static final String REQUEST_JQGRID_PAGE_SIZE = "rows" ;

    /**
     * 请求时，代表当前页数的key
     */
    public static final String REQUEST_JQGRID_CURRENT_PAGE = "page" ;

    /**
     * 请求时，代表排序名称的key
     */
    public static final String REQUEST_JQGRID_SORT_NAME = "sidx" ;

    /**
     * 请求时，代表排序顺序的key
     *  value： asc，desc
     */
    public static final String REQUEST_JQGRID_SORT_ORDER = "sord" ;

}
