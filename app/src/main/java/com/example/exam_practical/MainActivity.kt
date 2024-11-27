package com.example.exam_practical

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.exam_practical.ui.theme.Exam_PracticalTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Exam_PracticalTheme {
                EventScreen()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EventScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("EventEase", fontSize = 20.sp, fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = { /* Handle back action */ }) {
                        Icon(
                            painter = painterResource(id = android.R.drawable.ic_menu_revert),
                            contentDescription = "Back"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { /* Handle share action */ }) {
                        Icon(
                            painter = painterResource(id = android.R.drawable.ic_menu_share),
                            contentDescription = "Share"
                        )
                    }
                }
            )
        },
        content = { innerPadding ->
            // Using LazyColumn for scrollable content
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .background(Color(0xFFF7F7F7)) // Set background color
            ) {
                item {
                    // Banner Image
                    Image(
                        painter = painterResource(id = R.drawable.ic_menu_gallery), // Replace with actual image
                        contentDescription = "Event Banner",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp),
                        contentScale = ContentScale.Crop
                    )
                }

                item {
                    // Event Details
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = "Tech Conference 2024",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Mehsana, Gujarat | 2.5 km away",
                            fontSize = 16.sp,
                            color = Color.Gray
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = "This is a detailed description of the event...",
                            fontSize = 14.sp
                        )
                    }
                }

                item {
                    // Event Schedule
                    Text(
                        text = "Event Schedule",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(16.dp)
                    )

                    LazyRow(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        item { EventCard("09:00 AM", "Opening Ceremony", "Kick-off the event") }
                        item { EventCard("10:00 AM", "Keynote Speech", "Keynote by our esteemed speaker") }
                        item { EventCard("11:30 AM", "Networking Session", "Meet and connect") }
                    }
                }

                item {
                    // Reviews Section
                    Text(
                        text = "Reviews",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(16.dp)
                    )

                    Column(
                        modifier = Modifier.padding(horizontal = 16.dp)
                    ) {
                        ReviewCard("Alice Johnson", "Great event! Well-organized and informative.", 5)
                        ReviewCard("Bob Smith", "Really enjoyed the keynote speaker. Would recommend!", 5)
                        ReviewCard("Charlie Davis", "Good event overall, but some sessions were too short.", 4)
                    }
                }

                item {
                    // Buttons
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Button(
                            onClick = { /* Handle Buy Tickets */ },
                            modifier = Modifier.weight(1f)
                        ) {
                            Text("Buy Tickets")
                        }

                        Button(
                            onClick = { /* Handle Add to Calendar */ },
                            modifier = Modifier.weight(1f)
                        ) {
                            Text("Add to Calendar")
                        }
                    }
                }
            }
        }
    )
}

@Composable
fun EventCard(time: String, title: String, description: String) {
    Surface(
        modifier = Modifier
            .width(160.dp)
            .height(100.dp),
        shape = RoundedCornerShape(8.dp),
        shadowElevation = 4.dp,
        color = Color.White
    ) {
        Column(
            modifier = Modifier.padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(text = time, fontSize = 14.sp, fontWeight = FontWeight.Bold, color = Color.Gray)
            Text(text = title, fontSize = 16.sp, fontWeight = FontWeight.Bold)
            Text(text = description, fontSize = 12.sp, color = Color.Gray)
        }
    }
}

@Composable
fun ReviewCard(name: String, reviewText: String, rating: Int) {
    Column(modifier = Modifier.padding(vertical = 8.dp)) {
        // User Profile Picture and Name + Rating (Stars)
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(id = R.drawable.ic_menu_info_details), // Placeholder for Profile Picture
                contentDescription = "User",
                modifier = Modifier
                    .size(30.dp)
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.width(8.dp))
            // Name and Stars (Rating)
            Column {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = name, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.width(8.dp))
                    // Display stars next to the name
                    Row {
                        repeat(rating) {
                            Icon(
                                painter = painterResource(id = android.R.drawable.star_on),
                                contentDescription = "Star",
                                modifier = Modifier.size(16.dp)
                            )
                        }
                    }
                }
            }
        }

        // Review Text below the Name and Rating Stars
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = reviewText)
    }
}

@Preview(showBackground = true)
@Composable
fun EventScreenPreview() {
    Exam_PracticalTheme {
        EventScreen()
    }
}
