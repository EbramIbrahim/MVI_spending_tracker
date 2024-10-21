package com.example.spendingapp.features.spending_overview.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.spendingapp.ui.theme.montserrat


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SpendingOverviewTopBar(
    modifier: Modifier = Modifier,
    scrollBehavior: TopAppBarScrollBehavior,
    onBalanceClicked:() -> Unit,
    balance: Double = 0.0
) {

    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Transparent,
            scrolledContainerColor = Color.Transparent
        ),
        scrollBehavior = scrollBehavior,
        title = {
            Text(
                text = "$${balance}",
                fontFamily = montserrat,
                fontSize = 35.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                color = MaterialTheme.colorScheme.primary
            )
        },
        actions = {
            Box(
                modifier = Modifier
                    .size(45.dp)
                    .border(
                        width = 2.dp,
                        color = MaterialTheme.colorScheme.primary.copy(0.6f),
                        shape = RoundedCornerShape(13.dp)
                    )
                    .background(
                        MaterialTheme.colorScheme.primaryContainer.copy(0.3f)
                    )
                    .clickable { onBalanceClicked() },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "$",
                    fontSize = 26.sp,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.SemiBold
                )
            }
        },
        modifier = modifier.padding(end = 16.dp, start = 13.dp)
    )
}







