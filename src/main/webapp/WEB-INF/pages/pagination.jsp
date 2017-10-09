<div class="btn-toolbar justify-content-center" role="toolbar" aria-label="Toolbar with button groups">
    <c:set var="current_is_1st" value="${current_page > 1}"/>
    <c:set var="current_is_last" value="${current_page == pages_count}"/>
    <c:set var="maxViewPages" value="5"/>

    <c:choose>
        <%-- Only for pagination with more then maxViewPages pages (showing 'prev' and 'next' buttons) --%>
        <c:when test="${pages_count > maxViewPages}">
            <%-- First button 'To_first_page' --%>
            <c:choose>
                <c:when test="${current_is_1st}">
                    <a class="btn btn-outline-dark mr-3 btn-w-1 btn-h-1"
                       href="${pageContext.request.contextPath}?page=1" role="button">
                        <i class="material-icons">first_page</i>
                    </a>
                </c:when>
                <c:otherwise>
                    <a class="btn btn-outline-secondary mr-3 btn-w-1 btn-h-1 disabled"
                       href="#" role="button">
                        <i class="material-icons">first_page</i>
                    </a>
                </c:otherwise>
            </c:choose>

            <%-- Group of pages buttons : f.e. '1' '2' '3' ..., including current page --%>
            <%-- TODO --%>
            <div class="btn-group mr-3" role="group" aria-label="pages group">
                    <%-- First '...' button --%>
                <c:if test="${current_page > maxViewPages}">
                    <a class="btn btn-outline-secondary btn-w-1 btn-h-1 disabled"
                       href="#" role="button">
                        <i class="material-icons">more_horiz</i>
                    </a>
                </c:if>

                <c:set var="start" value="${1}"/>
                <c:set var="end" value="${pages_count}"/>
                <c:if test="${end > maxViewPages}">
                    <c:set var="end" value="${maxViewPages}"/>
                </c:if>
                <c:if test="${current_page - maxViewPages + 1 > start}">
                    <c:set var="start" value="${pages_count - maxViewPages + 1}"/>
                </c:if>
                <c:forEach var="i" begin="${start}" end="${end}">
                    <c:choose>
                        <c:when test="${i == current_page}">
                            <a class="btn btn-outline-dark btn-w-1 btn-h-1 active disabled"
                               href="#" role="button">
                                <h5>${i}</h5>
                            </a>
                        </c:when>
                        <c:otherwise>
                            <a class="btn btn-outline-dark btn-w-1 btn-h-1"
                               href="${pageContext.request.contextPath}?page=${i}" role="button">
                                <h5>${i}</h5>
                            </a>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
                    <%-- Last '...' button --%>
                <c:if test="${pages_count - maxViewPages + 1 > current_page}">
                    <a class="btn btn-outline-secondary btn-w-1 btn-h-1 disabled"
                       href="#" role="button">
                        <i class="material-icons">more_horiz</i>
                    </a>
                </c:if>
            </div>

            <%-- Showing 'To_last_last' button --%>
            <c:choose>
                <c:when test="${current_page < pages_count}">
                    <a class="btn btn-outline-dark btn-w-1 btn-h-1"
                       href="${pageContext.request.contextPath}?page=${pages_count}" role="button">
                        <i class="material-icons">last_page</i>
                    </a>
                </c:when>
                <c:otherwise>
                    <a class="btn btn-outline-secondary btn-w-1 btn-h-1 disabled"
                       href="#" role="button">
                        <i class="material-icons">last_page</i>
                    </a>
                </c:otherwise>
            </c:choose>
        </c:when>
        <c:otherwise>
            <div class="btn-group mr-3" role="group" aria-label="pages group">
                <c:forEach var="i" begin="${1}" end="${pages_count}">
                    <c:choose>
                        <c:when test="${i == current_page}">
                            <a class="btn btn-outline-dark btn-w-1 btn-h-1 active disabled"
                               href="#" role="button">
                                <h5>${i}</h5>
                            </a>
                        </c:when>
                        <c:otherwise>
                            <a class="btn btn-outline-dark btn-w-1 btn-h-1"
                               href="${pageContext.request.contextPath}?page=${i}" role="button">
                                <h5>${i}</h5>
                            </a>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </div>
        </c:otherwise>
    </c:choose>
    <%--</div>--%>
</div>