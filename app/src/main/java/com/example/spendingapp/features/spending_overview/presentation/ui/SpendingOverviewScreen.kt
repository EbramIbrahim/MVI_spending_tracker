package com.example.spendingapp.features.spending_overview.presentation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import com.example.spendingapp.core.presentation.util.Background
import com.example.spendingapp.features.spending_overview.presentation.viewmodel.SpendingOverviewAction
import com.example.spendingapp.features.spending_overview.presentation.viewmodel.SpendingOverviewState
import com.example.spendingapp.features.spending_overview.presentation.viewmodel.SpendingOverviewViewModel
import org.koin.androidx.compose.koinViewModel


@Composable
fun SpendingOverviewCore(
    viewModel: SpendingOverviewViewModel = koinViewModel(),
    onBalanceClick: () -> Unit,
    onAddSpendingClick: () -> Unit,
) {

    LaunchedEffect(key1 = true) {
        viewModel.onAction(
            SpendingOverviewAction.LoadingSpendingOverviewAndBalance
        )
    }

    SpendingOverviewScreen(
        state = viewModel.state,
        onAction = viewModel::onAction,
        onBalanceClick = { onBalanceClick() },
        onAddSpendingClick = { onAddSpendingClick() },
        onDeleteSpending = {
            viewModel.onAction(SpendingOverviewAction.OnDataDeleted(it))
        }
    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SpendingOverviewScreen(
    state: SpendingOverviewState,
    onAction: (SpendingOverviewAction) -> Unit,
    onBalanceClick: () -> Unit,
    onAddSpendingClick: () -> Unit,
    onDeleteSpending: (Int) -> Unit,
) {

    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(
        state = rememberTopAppBarState()
    )

    Scaffold(
        modifier = Modifier.nestedScroll(
            scrollBehavior.nestedScrollConnection
        ),
        floatingActionButton = {
            Column {
                FloatingActionButton(
                    onClick = { onAddSpendingClick() }
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Add Spending"
                    )
                }
                Spacer(modifier = Modifier.height(40.dp))
            }
        },
        topBar = {
            SpendingOverviewTopBar(
                modifier = Modifier.fillMaxWidth(),
                scrollBehavior = scrollBehavior,
                onBalanceClicked = onBalanceClick,
                balance = state.balance
            )
        }
    ) { paddingValues ->
        Background()
        Column(
            modifier = Modifier.padding(paddingValues)
        ) {

        }
    }

}













