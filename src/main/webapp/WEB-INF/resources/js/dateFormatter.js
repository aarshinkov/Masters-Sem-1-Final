/**
 * Created by Atanas Yordanov Arshinkov
 */

/**
 * Gets a date in standard format and converts it in format dd.MM.YYYY HH:mm
 *
 * @param fullDate the date to be formatted
 * @returns {string} the formatted date as string
 */
function formatDate(fullDate) {
    const d = new Date(fullDate);
    const year = d.getFullYear();
    const month = addLeadingZero(d.getMonth() + 1);
    const date = addLeadingZero(d.getDate());
    const hour = addLeadingZero(d.getHours());
    const minutes = addLeadingZero(d.getMinutes());

    return date + '.' + month + '.' + year + ' ' + hour + ':' + minutes;
}

/**
 * Adds a leading zero in front of a number if necessary e.g. 4 -> 04, 7 -> 07, 11 -> 11
 * @param number the number to be added a leading zero
 * @returns {string} number with the leading zero (if necessary)
 */
function addLeadingZero(number) {
    if (number < 10) {
        return '0' + number;
    } else {
        return number;
    }
}