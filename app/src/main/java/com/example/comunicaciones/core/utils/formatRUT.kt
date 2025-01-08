package com.example.comunicaciones.core.utils


fun formatRut(input: String): String {
    // Remove any characters that are not numbers or 'K'
    val filteredInput = input.filter { it.isDigit() || it.equals('K', ignoreCase = true) }

    // Check if there's anything to format
    if (filteredInput.isEmpty()) return ""

    // Separate the number part and the verification digit (if present)
    val numberPart = if (filteredInput.length > 1) filteredInput.dropLast(1) else filteredInput
    val verificationDigit = if (filteredInput.length > 1) filteredInput.last().toString() else ""

    // Insert points for every thousand and keep the last character as verification digit
    val formattedNumber = numberPart.reversed().chunked(3).joinToString(".").reversed()

    return if (verificationDigit.isNotEmpty() && numberPart.isNotEmpty()) "$formattedNumber-$verificationDigit" else formattedNumber
}

fun calculateDV(rut: String): String {
    val reversedRut = rut.reversed()
    var sum = 0
    var multiplier = 2

    for (char in reversedRut) {
        sum += Character.getNumericValue(char) * multiplier
        multiplier = if (multiplier == 7) 2 else multiplier + 1
    }

    val remainder = 11 - (sum % 11)

    return when (remainder) {
        11 -> "0"
        10 -> "K"
        else -> remainder.toString()
    }
}

fun isValidRut(rut: String): Boolean {
    if (rut.isEmpty()) return false

    val rutNumber = rut.dropLast(1).toIntOrNull() ?: return false
    val dv = rut.last().uppercaseChar()

    return calculateDV(rutNumber.toString()) == dv.toString()
}

