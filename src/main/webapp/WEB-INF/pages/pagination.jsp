<div class="btn-toolbar justify-content-center" role="toolbar" aria-label="Toolbar with button groups">
    <c:set var="current_is_1st" value="${current_page > 1}"/>
    <c:set var="current_is_last" value="${current_page == pages_count}"/>
    <c:set var="maxViewPages" value="10"/>

    <%-- Only for pagination with 10+ pages (showing 'prev' and 'next' buttons) --%>
    <%-- First button 'To_first_page' --%>
    <div class="btn-group mr-3" role="group" aria-label="First group">
        <c:choose>
            <c:when test="${current_is_1st}">
                <a class="btn btn-outline-dark w-btn-corr h-btn-corr"
                   href="${pageContext.request.contextPath}?page=1" role="button">
                    <i class="material-icons">first_page</i>
                </a>
            </c:when>
            <c:otherwise>
                <a class="btn btn-outline-secondary w-btn-corr h-btn-corr disabled"
                   href="#" role="button">
                    <i class="material-icons">first_page</i>
                </a>
            </c:otherwise>
        </c:choose>
    </div>

    <%-- Group of pages buttons : f.e. '1' '2' '3' ..., including current page --%>
    <div class="btn-group mr-3" role="group" aria-label="pages group">
        <%-- First '...' button --%>
        <c:if test="${current_page > maxViewPages}">
            <a class="btn btn-outline-secondary w-btn-corr h-btn-corr disabled"
               href="#" role="button">
                <i class="material-icons">more_horiz</i>
            </a>
        </c:if>

        <c:set var="start" value="1"/>
        <c:set var="end" value="${pages_count}"/>
        <c:if test="${end > maxViewPages}">
            <c:set var="end" value="${maxViewPages}"/>
        </c:if>
        <c:if test="${pages_count - maxViewPages + 1 > start}">
            <c:set var="start" value="${pages_count - maxViewPages + 1}"/>
        </c:if>
        <%-- <a class="btn btn-outline-warning disabled hidden" href="#">
            cp:${current_page} pc:${pages_count} s:${start} e:${end}
        </a> --%>
        <c:forEach var="i" begin="${start}" end="${end}">
            <c:choose>
                <c:when test="${i == current_page}">
                    <a class="btn btn-outline-dark w-btn-corr h-btn-corr active disabled"
                       href="#" role="button">
                        <h5>${i}</h5>
                    </a>
                </c:when>
                <c:otherwise>
                    <a class="btn btn-outline-dark w-btn-corr h-btn-corr"
                       href="${pageContext.request.contextPath}?page=${i}" role="button">
                        <h5>${i}</h5>
                    </a>
                </c:otherwise>
            </c:choose>
        </c:forEach>
        <%-- <a class="btn btn-outline-dark active" href="#" role="button">
            <h5>${current_page} pojo</h5>
        </a> --%>
    </div>

    <%-- Only for pagination with 10+ pages (showing 'To_last_last' button) --%>
    <div class="btn-group" role="group" aria-label="Last group">
        <c:choose>
            <c:when test="${current_page < pages_count}">
                <a class="btn btn-outline-dark w-btn-corr h-btn-corr"
                   href="${pageContext.request.contextPath}?page=${pages_count}" role="button">
                    <i class="material-icons">last_page</i>
                </a>
            </c:when>
            <c:otherwise>
                <a class="btn btn-outline-secondary w-btn-corr h-btn-corr disabled"
                   href="#" role="button">
                    <i class="material-icons">last_page</i>
                </a>
            </c:otherwise>
        </c:choose>
    </div>
    <%--<a href="${pageContext.request.contextPath}/news" class="btn btn-outline-dark" role="button">1</a>--%>
</div>

<%--
<table>
    <tr>
        <c:if test="${current_page > 1}">
            <th>
                <form action="${pageContext.request.contextPath}/news" method="get">
                    <input type="number" name="page" value="1" hidden>
                    <input type="submit" value="First page">
                </form>
            </th>
            <th>
                <form action="${pageContext.request.contextPath}/news" method="get">
                    <input type="number" name="page" value="${current_page -1}" hidden>
                    <input type="submit" value="Previous page">
                </form>
            </th>
        </c:if>

        <th>
            Page ${current_page} of ${pages_count}
        </th>

        <c:if test="${current_page < pages_count}">
            <th>
                <form action="${pageContext.request.contextPath}/news" method="get">
                    <input type="number" name="page" value="${current_page + 1}" hidden>
                    <input type="submit" value="Next page">
                </form>
            </th>
            <th>
                <form action="${pageContext.request.contextPath}/news" method="get">
                    <input type="number" name="page" value="${pages_count}" hidden>
                    <input type="submit" value="Last page">
                </form>
            </th>
        </c:if>
    </tr>
</table>
--%>