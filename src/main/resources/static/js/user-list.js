var USER_JSON = "https://shiftstestapi.firebaseio.com/users.json";
var API_URL = "localhost:8090/api/v1/hours/";

$(document).ready(function() {
    loadUsers();
});

function loadUsers(){
    $.getJSON(USER_JSON, function( data ) {
        var items = [];
        $.each( data, function( key, val ) {
            $.each( val, function( keyUser, user ) {
                loadWorkedHours(user)
            });
        });
    });
}

function loadWorkedHours(user){
    console.log(user);
    $.getJSON(API_URL + user.id, function( workedHours ) {
        console.log(data);

        var details = getDetailsHeader();
        $.each( workedHours, function( keyWorkedHours, workedHoursElement ) {
            loadWorkedHours(user)
            details += "<tr>" +
                           "<td>" + workedHoursElement.weekStart + "</td>" +
                           "<td>" + workedHoursElement.weekEnd + "</td>" +
                           "<td>" + workedHoursElement.regularHours + "</td>" +
                           "<td>" + workedHoursElement.dailyOvertime + "</td>" +
                           "<td>" + workedHoursElement.weeklyOvertime + "</td>" +
                       "</tr>";
        });

        details += getDetailsFooter();

        $("#userList tbody").append(
            "<tr>" +
                "<td>" + user.id + "</td>" +
                "<td>" + user.firstName + " " + user.lastName + "</td>" +
            "</tr>" +
            "<tr>" +
                "<td colspan=\"2\">" + details + "</td>" +
            "</tr>"
        );
    });
}

function getDetailsHeader(){
    return "<table id=\"userList\">" +
                "<thead>" +
                     "<th>" +
                         "<td>Week Start</td>" +
                         "<td>Week End</td>" +
                         "<td>Regular Hours</td>" +
                         "<td>Daily Overtime</td>" +
                         "<td>Weekly Overtime</td>" +
                     "</th>" +
                 "</thead>" +
                 "<tbody>";
}

function getDetailsFooter(){
    return "</body>" +
           "</table>";
}