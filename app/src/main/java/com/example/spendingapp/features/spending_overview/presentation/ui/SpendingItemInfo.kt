package com.example.spendingapp.features.spending_overview.presentation.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.spendingapp.core.domain.model.Spending
import com.example.spendingapp.features.spending_overview.presentation.viewmodel.SpendingOverviewState
import com.example.spendingapp.ui.theme.montserrat


@Composable
fun SpendingList(
    modifier: Modifier = Modifier,
    state: SpendingOverviewState,
    onDeleteSpending: (Int) -> Unit
) {

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(
            top = 20.dp, bottom = 80.dp
        )
    ) {
        itemsIndexed(state.spendingList) { index, spending ->
            SpendingItem(
                spending = spending,
                onDeleteSpending = { onDeleteSpending(index) }
            )
            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SpendingItem(
    modifier: Modifier = Modifier,
    spending: Spending,
    onDeleteSpending: () -> Unit
) {

    var isDeleteMenuShowing by rememberSaveable {
        mutableStateOf(false)
    }

    Box {
        ElevatedCard(
            shape = RoundedCornerShape(22.dp),
            elevation = CardDefaults.elevatedCardElevation(
                defaultElevation = 4.dp
            ),
            modifier = modifier
                .height(150.dp)
                .padding(horizontal = 16.dp)
                .combinedClickable(
                    onClick = {},
                    onLongClick = { isDeleteMenuShowing = !isDeleteMenuShowing }
                )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .background(Color(spending.color))
                    .padding(horizontal = 18.dp, vertical = 12.dp),
                verticalArrangement = Arrangement.SpaceAround
            ) {
                Text(
                    text = spending.name,
                    maxLines = 1,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium,
                    overflow = TextOverflow.Ellipsis,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Spacer(modifier = Modifier.height(1.dp))

                SpendingInfo(
                    title = "Price",
                    spending = "$${spending.price}"
                )

                SpendingInfo(
                    title = "Kilograms",
                    spending = "${spending.kilogram}"
                )

                SpendingInfo(
                    title = "Quantity",
                    spending = "${spending.quantity}"
                )
            }

        }

        DropdownMenu(
            expanded = isDeleteMenuShowing,
            onDismissRequest = { isDeleteMenuShowing = false },
            offset = DpOffset(30.dp, 0.dp)
        ) {

            DropdownMenuItem(
                text = {
                    Text(text = "Delete Spending", fontFamily = montserrat)
                },
                onClick = { onDeleteSpending() }
            )

        }
    }

}


@Composable
fun SpendingInfo(
    title: String,
    spending: String
) {

    Row {

        Text(
            text = "$title :",
            fontSize = 16.sp,
            maxLines = 1,
            fontWeight = FontWeight.Normal,
            overflow = TextOverflow.Ellipsis,
            color = MaterialTheme.colorScheme.onBackground.copy(0.8f)
        )

        Text(
            text = spending,
            fontSize = 18.sp,
            maxLines = 1,
            fontWeight = FontWeight.Normal,
            overflow = TextOverflow.Ellipsis,
            color = MaterialTheme.colorScheme.onBackground
        )
    }

}










