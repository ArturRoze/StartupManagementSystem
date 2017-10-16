<c:choose>
    <c:when test="${check == null || check.trim().equals('')}">
        <span class="text-gr"><em>[empty]</em></span>
    </c:when>
    <%--<c:when test="${check == null}">
        <span class="text-danger"><em>[null]</em></span>
    </c:when>
    <c:when test="${!check.trim().equals('') && check.length() == 0}">
        <span class="text-warning"><em>?????</em></span>
    </c:when>--%>
    <c:otherwise>${check}</c:otherwise>
</c:choose>