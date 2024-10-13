package com.example.spendingapp.features.balance.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.spendingapp.core.presentation.util.Background
import com.example.spendingapp.ui.theme.montserrat
import org.koin.androidx.compose.koinViewModel


@Composable
fun BalanceScreenCore(
    viewModel: BalanceViewModel = koinViewModel(),
    onSaveClick: () -> Unit
) {

    BalanceElementScreen(
        balanceState = viewModel.balanceState,
        onAction = viewModel::onAction,
        onSaveClick = {
            viewModel.onAction(BalanceAction.OnBalanceSaved)
            onSaveClick()
        }
    )

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BalanceElementScreen(
    balanceState: BalanceState,
    onAction: (BalanceAction) -> Unit,
    onSaveClick: () -> Unit
) {

    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier.padding(start = 12.dp),
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent,
                    scrolledContainerColor = Color.Transparent
                ),
                title = {
                    Text(
                        text = "UpdateBalance",
                        fontFamily = montserrat,
                        fontSize = 26.sp,
                        color = MaterialTheme.colorScheme.primary

                    )
                }
            )
        }
    ) { padding ->

        Background()

        Column(
            modifier = Modifier
                .padding(padding)
                .padding(horizontal = 12.dp)
        ) {

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "$${balanceState.balance}",
                fontWeight = FontWeight.SemiBold,
                fontSize = 40.sp,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(38.dp))

            OutlinedTextField(
                value = balanceState.balance.toString(),
                onValueChange = {
                    onAction(
                        BalanceAction.OnBalanceChanged(
                            it.toDoubleOrNull() ?: 0.0
                        )
                    )
                },
                label = {
                    Text(text = "Enter balance")
                },
                textStyle = TextStyle(fontSize = 18.sp),
                maxLines = 1,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Decimal
                ),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(38.dp))

            OutlinedButton(onClick = {
                onSaveClick()
            }) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = "save balance",
                        modifier = Modifier.size(33.dp)
                    )
                    Spacer(modifier = Modifier.width(2.dp))

                    Text(text = "Save Balance", fontSize = 20.sp)

                }

            }

        }

    }


}











