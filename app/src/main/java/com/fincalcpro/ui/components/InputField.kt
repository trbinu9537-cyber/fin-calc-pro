package com.fincalcpro.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    prefix: String = "₹",
    showPrefix: Boolean = true,
    keyboardType: KeyboardType = KeyboardType.Decimal,
    isError: Boolean = false,
    errorMessage: String = "",
    suffix: String = ""
) {
    Column(modifier = modifier) {
        OutlinedTextField(
            value = value,
            onValueChange = { newValue ->
                // Allow only numbers and decimal point
                if (newValue.isEmpty() || newValue.matches(Regex("^\\d*\\.?\\d*$"))) {
                    onValueChange(newValue)
                }
            },
            label = { Text(label) },
            prefix = if (showPrefix && prefix.isNotEmpty()) {
                { Text(prefix) }
            } else null,
            suffix = if (suffix.isNotEmpty()) {
                { Text(suffix) }
            } else null,
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            isError = isError,
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = MaterialTheme.colorScheme.primary,
                unfocusedBorderColor = MaterialTheme.colorScheme.outline,
                errorBorderColor = MaterialTheme.colorScheme.error
            )
        )
        
        if (isError && errorMessage.isNotEmpty()) {
            Text(
                text = errorMessage,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(start = 16.dp, top = 4.dp)
            )
        }
    }
}

@Composable
fun PercentageInputField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    isError: Boolean = false,
    errorMessage: String = ""
) {
    InputField(
        value = value,
        onValueChange = onValueChange,
        label = label,
        modifier = modifier,
        prefix = "",
        showPrefix = false,
        suffix = "%",
        isError = isError,
        errorMessage = errorMessage
    )
}

@Composable
fun YearInputField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    isError: Boolean = false,
    errorMessage: String = ""
) {
    InputField(
        value = value,
        onValueChange = onValueChange,
        label = label,
        modifier = modifier,
        prefix = "",
        showPrefix = false,
        suffix = "Years",
        keyboardType = KeyboardType.Number,
        isError = isError,
        errorMessage = errorMessage
    )
}
