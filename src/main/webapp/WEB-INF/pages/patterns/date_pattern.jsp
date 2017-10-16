<c:if test="${dateOf.date < 10}">0</c:if>${dateOf.date}-<c:if test="${dateOf.month < 10}">0</c:if>${dateOf.month}-${dateOf.year + 1900}&nbsp;<c:if test="${dateOf.hours < 10}">0</c:if>${dateOf.hours}:<c:if test="${dateOf.minutes < 10}">0</c:if>${dateOf.minutes}:<c:if test="${dateOf.seconds < 10}">0</c:if>${dateOf.seconds}
<%--
toOffs: ${dateOf.timezoneOffset}, date: ${dateOf.date}<br>
tm: ${dateOf.time}, m: ${dateOf.minutes}<br>
h: ${dateOf.hours}, y: ${dateOf.year}<br>
m: ${dateOf.month}, d: ${dateOf.day}
--%>