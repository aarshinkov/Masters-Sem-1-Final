function isBlank(input) {
    return input.replace(/\s/g, '').length === 0;
}

function showError(input, errorLabel, error) {
    $(input).addClass("is-invalid");
    $(errorLabel).addClass("invalid-feedback");
    $(errorLabel).text(error);
    return true;
}

function removeError(input, errorLabel) {
    $(input).removeClass("is-valid");
    $(input).removeClass("is-invalid");
    $(errorLabel).removeClass("invalid-feedback");
    $(errorLabel).text("");
    return false;
}