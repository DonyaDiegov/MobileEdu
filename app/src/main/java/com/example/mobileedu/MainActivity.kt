package com.example.mobileedu

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mobileedu.ui.theme.MobileEduTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MobileEduTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                }
            }
        }
    }
}

data class ShoppingListItem(
    val description: String,
    val bought: Boolean = false
)

@Composable
fun App() {
    val shoppingList = remember {
        mutableStateListOf(ShoppingListItem("Молоко"), ShoppingListItem("Мука"))
    }
    var newItemDesc by remember { mutableStateOf("") }
    LazyColumn {
        item {
            OutlinedTextField(
                value = newItemDesc, onValueChange = { newItemDesc = it },
                modifier = Modifier.padding(8.dp),
                label = {
                    Text("Название продукта")
                },
                trailingIcon = {
                    IconButton(onClick = {
                        if (newItemDesc.isNotBlank()) {
                            shoppingList.add(ShoppingListItem(newItemDesc.trim()))
                            newItemDesc = ""
                        }
                    }) {
                        Icon(Icons.Default.Add, contentDescription = "Добавить")
                    }
                })
        }
        itemsIndexed(shoppingList) { i, item ->
            ShoppingListElement(
                item,
                onBoughtChange = {
                    shoppingList[i] = item.copy(bought = it)
                },
                onDelete = {
                    shoppingList.removeAt(i)
                }
            )
        }
    }
}

@Composable
fun ShoppingListElement(item: ShoppingListItem, onBoughtChange: (Boolean) -> Unit, onDelete: () -> Unit) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Checkbox(
            checked = item.bought,
            onCheckedChange = onBoughtChange
        )
        Text(item.description, Modifier.weight(1f))
        IconButton(onClick = onDelete) {
            // contentDescription не отображается на экране, но читается средствами помощи слепым
            Icon(Icons.Default.Delete, contentDescription = "Удалить")
        }
    }
}
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MobileEduTheme {
        App()
    }
}