/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import com.example.androiddevchallenge.ui.theme.MyTheme
import com.example.androiddevchallenge.ui.theme.customFontFamily
import dev.chrisbanes.accompanist.insets.ProvideWindowInsets
import dev.chrisbanes.accompanist.insets.navigationBarsPadding
import dev.chrisbanes.accompanist.insets.statusBarsPadding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            MyTheme {
                ProvideWindowInsets {
                    MyApp()
                }
            }
        }
    }
}

// Start building your app here!
@Composable
fun MyApp() {
    val navController = rememberNavController()
    NavHost(navController = navController, "listScreen") {
        composable("listScreen") { ListScreen(navController = navController) }
        composable(
            "detailScreen/{dogIndex}",
            arguments = listOf(navArgument("dogIndex") { type = NavType.IntType })
        ) {
            val dogIndex = it.arguments?.getInt("dogIndex") ?: 0
            DetailScreen(dogList[dogIndex])
        }
    }
}

@Composable
fun ListScreen(navController: NavController) {

    Scaffold(
        backgroundColor = MaterialTheme.colors.background,
        topBar = { ListScreenTopLayout() },
        bottomBar = { ListScreenBottomLayout() }
    ) {

        ListScreenContent(dogs = dogList, navController = navController)
    }
    Row(
        horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterHorizontally),
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(top = 63.dp)
            .statusBarsPadding()
            .height(height = 56.dp)
            .fillMaxWidth()
            .padding(horizontal = 24.dp)

    ) {
        Surface(
            color = MaterialTheme.colors.secondary,
            elevation = 4.dp,
            shape = RoundedCornerShape(size = 12.dp),
            modifier = Modifier
                .weight(1f, true)
                .height(height = 48.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()
                    .padding(start = 24.dp)
            ) {

                Icon(
                    modifier = Modifier
                        .size(24.dp),
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_round_location_on_24),
                    contentDescription = "Menu",
                    tint = MaterialTheme.colors.primary
                )

                Text(
                    text = "Ankara",
                    color = MaterialTheme.colors.primary,
                    fontWeight = FontWeight.Bold,
                    fontFamily = customFontFamily
                )
                Text(
                    text = ", Türkiye",
                    color = MaterialTheme.colors.onSurface,
                    fontWeight = FontWeight.Bold,
                    fontFamily = customFontFamily
                )
            }
        }
        Surface(
            color = MaterialTheme.colors.secondary,
            elevation = 4.dp,
            shape = RoundedCornerShape(size = 12.dp),
            modifier = Modifier.size(size = 48.dp)
        ) {
            Icon(
                modifier = Modifier
                    .size(24.dp)
                    .padding(all = 12.dp),
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_round_tune_24),
                contentDescription = "Menu",
                tint = MaterialTheme.colors.onSurface

            )
        }
    }
}

@Composable
fun ListScreenTopLayout() {

    Surface(
        elevation = 3.dp, shape = RoundedCornerShape(bottomStart = 8.dp, bottomEnd = 8.dp),
        modifier = Modifier
            .statusBarsPadding()
            .height(96.dp)
    ) {
        Column(
            Modifier
                .fillMaxHeight()
                .fillMaxWidth()
        ) {

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .height(height = 56.dp)
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
            ) {
                Box(contentAlignment = Alignment.CenterStart) {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_round_pets_24),
                        tint = MaterialTheme.colors.primaryVariant,
                        contentDescription = "App Logo",
                        modifier = Modifier
                            .size(24.dp)
                            .rotate(-22f)
                    )
                    Text(
                        text = "Pati",
                        modifier = Modifier.offset(x = 16.dp),
                        style = TextStyle(
                            color = MaterialTheme.colors.primary,
                            fontSize = 28.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily.Cursive
                        )
                    )
                }
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_round_menu_24),
                    contentDescription = "Menu",
                    tint = Color.Black,
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    }
}

@Composable
fun ListScreenBottomLayout() {
    Surface(elevation = 32.dp, shape = RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp)) {
        Row(
            Modifier
                .navigationBarsPadding()
                .height(height = 56.dp)
                .fillMaxWidth()
                .padding(end = 32.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                modifier = Modifier
                    .background(
                        color = MaterialTheme.colors.primary,
                        shape = RoundedCornerShape(
                            topStartPercent = 0,
                            topEndPercent = 50,
                            bottomEndPercent = 50,
                            bottomStartPercent = 0
                        )
                    )
                    .padding(start = 24.dp, end = 24.dp, top = 8.dp, bottom = 8.dp)
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_round_home_24),
                    contentDescription = "Tab 1",
                    tint = Color.White,
                    modifier = Modifier.size(24.dp)
                )
                Text(
                    text = "Home",
                    color = Color.White,
                    fontFamily = customFontFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 14.sp
                )
            }

            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_round_chat_bubble_24),
                tint = Color.Gray,
                contentDescription = "Tab 2",
                modifier = Modifier
                    .size(
                        24.dp
                    )
                    .offset(x = Dp(-4f))
            )
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_round_favorite_24),
                tint = Color.Gray,
                contentDescription = "Tab 3",
                modifier = Modifier
                    .size(
                        24.dp
                    )
                    .offset(x = Dp(-4f))
            )
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_round_person_24),
                tint = Color.Gray,
                contentDescription = "Tab 4",
                modifier = Modifier
                    .size(
                        24.dp
                    )
                    .offset(x = Dp(-4f))
            )
        }
    }
}

@Composable
fun ListScreenContent(dogs: List<Dog>, navController: NavController) {
    Column(
        Modifier
            .verticalScroll(rememberScrollState())
            .padding(bottom = 56.dp)
            .navigationBarsPadding(),
        Arrangement.spacedBy(8.dp)
    ) {
        Spacer(modifier = Modifier.size(size = 36.dp))
        Text(
            text = "“Dogs do speak, but only to those who know how to listen.”",
            color = Color.DarkGray,
            fontStyle = FontStyle.Italic,
            fontFamily = customFontFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 13.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 32.dp)
        )
        Spacer(modifier = Modifier.size(size = 0.dp))
        dogs.forEachIndexed { index, dog ->
            ListRow(dog = dog, index = index, rtl = index % 2 == 0, navController)
        }
        // Spacer(modifier = Modifier.size(size = 56.dp))
    }
}

@Composable
fun ListRow(dog: Dog, index: Int, rtl: Boolean, navController: NavController) {

    Row(
        verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .height(200.dp)
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
            .clickable(onClick = { navController.navigate(route = "detailScreen/$index") })
    ) {

        val modifier = Modifier.weight(1f, true)

        if (rtl) {
            ListRowImg(dog = dog)
            ListRowCard(dog = dog, rtl = rtl, modifier = modifier)
        } else {
            ListRowCard(dog = dog, rtl = rtl, modifier = modifier)
            ListRowImg(dog = dog)
        }
    }
}

@Composable
fun ListRowCard(dog: Dog, rtl: Boolean, modifier: Modifier) {

    val topLeftRad = if (rtl) 0.dp else 12.dp
    val topRightRad = if (rtl) 12.dp else 0.dp
    val bottomLeftRad = if (rtl) 0.dp else 12.dp
    val bottomRightRad = if (rtl) 12.dp else 0.dp

    Surface(
        Modifier
            .height(height = 150.dp)
            .then(modifier),
        shape = RoundedCornerShape(
            topStart = topLeftRad,
            topEnd = topRightRad,
            bottomStart = bottomLeftRad,
            bottomEnd = bottomRightRad
        ),
        color = MaterialTheme.colors.surface, elevation = 1.5.dp
    ) {

        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start,
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = dog.name,
                    style = TextStyle(
                        color = MaterialTheme.colors.onSurface,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = customFontFamily
                    )
                )

                val genderIconRes: Int
                val genderIconTint: Color
                if (dog.male) {
                    genderIconRes = R.drawable.ic_male_black_24dp
                    genderIconTint = Color(0xFF3F51B5)
                } else {
                    genderIconRes = R.drawable.ic_female_black_24dp
                    genderIconTint = Color(0xFFE91E63)
                }
                Icon(
                    imageVector = ImageVector.vectorResource(id = genderIconRes),
                    tint = genderIconTint,
                    contentDescription = "Gender Icon",
                    modifier = Modifier
                        .size(24.dp)
                )
            }

            Text(
                text = dog.type,
                style = TextStyle(
                    color = MaterialTheme.colors.primary,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Medium,
                    fontFamily = customFontFamily
                )
            )

            Text(
                text = dog.age,
                style = TextStyle(
                    color = Color.Gray,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Medium,
                    fontFamily = customFontFamily
                )
            )

            Box(Modifier.weight(1f, true), contentAlignment = Alignment.BottomStart) {

                Row(
                    horizontalArrangement = Arrangement.spacedBy(4.dp, alignment = Alignment.Start),
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()

                ) {

                    Image(
                        painter = painterResource(id = dog.ownerImgRes),
                        contentDescription = null,
                        modifier = Modifier
                            .size(width = 24.dp, height = 24.dp)
                            .clip(
                                RoundedCornerShape(size = 4.dp)
                            ),
                        contentScale = ContentScale.Crop
                    )

                    Text(
                        text = dog.ownerName,
                        style = TextStyle(
                            color = MaterialTheme.colors.onSurface,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = customFontFamily
                        )
                    )
                }
            }
        }
    }
}

@Composable
fun ListRowImg(dog: Dog) {

    Surface(
        Modifier.size(width = 150.dp, height = 200.dp),
        shape = RoundedCornerShape(size = 12.dp),
        color = MaterialTheme.colors.secondary,
        elevation = 5.dp
    ) {
        Image(
            painter = painterResource(id = dog.imgResList[0]), contentDescription = null,
            modifier = Modifier
                .size(width = 150.dp, height = 200.dp)
                .clip(
                    RoundedCornerShape(size = 12.dp)
                ),
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
fun DetailScreen(dog: Dog) {

    Surface(Modifier.navigationBarsPadding(), color = MaterialTheme.colors.background) {
        Column(
            Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            Box(
                Modifier
                    .weight(0.55f)
                    .fillMaxWidth()
            ) {
                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(),
                    painter = painterResource(id = dog.imgResList[0]),
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )
            }
            Column(
                Modifier
                    .fillMaxWidth()
                    .weight(0.45f)
                    .padding(bottom = 56.dp, start = 24.dp, end = 24.dp)
                    .verticalScroll(rememberScrollState())
            ) {

                Spacer(modifier = Modifier.height(height = 96.dp))

                Row(
                    Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.Start)
                    ) {
                        Image(
                            modifier = Modifier
                                .size(size = 48.dp)
                                .clip(RoundedCornerShape(12.dp)),
                            painter = painterResource(id = dog.ownerImgRes),
                            contentDescription = null,
                            contentScale = ContentScale.Crop
                        )
                        Column(horizontalAlignment = Alignment.Start) {
                            Text(
                                text = dog.ownerName,
                                style = TextStyle(
                                    color = MaterialTheme.colors.onSurface,
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.Bold,
                                    fontFamily = customFontFamily
                                )
                            )
                            Text(
                                text = "Owner",
                                style = TextStyle(
                                    color = Color.Gray,
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.Bold,
                                    fontFamily = customFontFamily
                                )
                            )
                        }
                    }
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.End)
                    ) {

                        Surface(
                            color = Color(0xFF4FC3F7),
                            elevation = 2.dp,
                            shape = RoundedCornerShape(size = 12.dp),
                            modifier = Modifier.size(size = 42.dp)
                        ) {
                            Icon(
                                modifier = Modifier
                                    .size(24.dp)
                                    .padding(all = 9.dp),
                                imageVector = ImageVector.vectorResource(id = R.drawable.ic_round_phone_24),
                                contentDescription = "phone",
                                tint = Color(0xFF01579B)

                            )
                        }

                        Surface(
                            color = Color(0xFFAED581),
                            elevation = 2.dp,
                            shape = RoundedCornerShape(size = 12.dp),
                            modifier = Modifier.size(size = 42.dp)
                        ) {
                            Icon(
                                modifier = Modifier
                                    .size(24.dp)
                                    .padding(all = 9.dp),
                                imageVector = ImageVector.vectorResource(id = R.drawable.ic_round_textsms_24),
                                contentDescription = "message",
                                tint = Color(0xFF33691E)

                            )
                        }
                    }
                }
                val hitap = if (dog.male) "He" else "She"
                Text(
                    modifier = Modifier.padding(top = 16.dp),
                    fontSize = 14.sp,
                    fontFamily = customFontFamily,
                    fontWeight = FontWeight.Medium,
                    text = "${dog.name} arrived to the shelter today. $hitap is a puppy and... in true puppy fashion, will need your time and dedication. $hitap is friendly to all people and has done well with other dogs. $hitap is described as very active and playful."
                )
            }
        }

        Box(
            Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            Alignment.BottomCenter
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterHorizontally),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .height(height = 56.dp)
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)

            ) {
                Surface(
                    color = MaterialTheme.colors.secondary,
                    elevation = 2.dp,
                    shape = RoundedCornerShape(size = 12.dp),
                    modifier = Modifier.size(size = 48.dp)
                ) {
                    Icon(
                        modifier = Modifier
                            .size(24.dp)
                            .padding(all = 12.dp),
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_round_favorite_border_24),
                        contentDescription = "fav",
                        tint = MaterialTheme.colors.onSurface

                    )
                }

                Surface(
                    color = MaterialTheme.colors.secondary,
                    elevation = 2.dp,
                    shape = RoundedCornerShape(size = 12.dp),
                    modifier = Modifier
                        .weight(1f, true)
                        .height(height = 48.dp)

                ) {
                    Box {
                        Text(
                            modifier = Modifier.align(Alignment.Center),
                            text = "ADOPT",
                            color = MaterialTheme.colors.onSurface,
                            fontWeight = FontWeight.Bold,
                            fontFamily = customFontFamily,
                            fontSize = 20.sp
                        )
                    }
                }
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            Box(
                modifier = Modifier
                    .weight(0.55f)
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp)
            ) {

                if (dog.imgResList.size > 1) {
                    Column(
                        modifier = Modifier
                            .align(alignment = Alignment.TopEnd)
                            .statusBarsPadding()
                            .offset(x = 8.dp, y = 12.dp),
                        verticalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        dog.imgResList.forEachIndexed { index, resId ->

                            val borderWidth = if (index == 0) 2f else 1f
                            val borderColor =
                                if (index == 0) Color.White else MaterialTheme.colors.primary
                            Image(
                                modifier = Modifier
                                    .size(size = 36.dp)
                                    .border(
                                        width = borderWidth.dp,
                                        color = borderColor,
                                        shape = RoundedCornerShape(4.dp)
                                    )
                                    .clip(RoundedCornerShape(4.dp)),
                                painter = painterResource(id = resId),
                                contentDescription = null,
                                contentScale = ContentScale.Crop
                            )
                        }
                    }
                }

                Surface(
                    elevation = 2.dp,
                    shape = RoundedCornerShape(12.dp),
                    color = MaterialTheme.colors.surface,
                    modifier = Modifier
                        .height(height = 96.dp)
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter)
                        .offset(y = 66.dp)
                ) {

                    Column(
                        verticalArrangement = Arrangement.Top,
                        horizontalAlignment = Alignment.Start,
                        modifier = Modifier.padding(vertical = 8.dp, horizontal = 24.dp)
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = dog.name,
                                style = TextStyle(
                                    color = MaterialTheme.colors.onSurface,
                                    fontSize = 22.sp,
                                    fontWeight = FontWeight.Bold,
                                    fontFamily = customFontFamily
                                )
                            )

                            val genderIconRes: Int
                            val genderIconTint: Color
                            if (dog.male) {
                                genderIconRes = R.drawable.ic_male_black_24dp
                                genderIconTint = Color(0xFF3F51B5)
                            } else {
                                genderIconRes = R.drawable.ic_female_black_24dp
                                genderIconTint = Color(0xFFE91E63)
                            }
                            Icon(
                                imageVector = ImageVector.vectorResource(id = genderIconRes),
                                tint = genderIconTint,
                                contentDescription = "Gender Icon",
                                modifier = Modifier
                                    .size(24.dp)
                            )
                        }

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(4.dp, Alignment.Start)
                        ) {

                            Text(
                                text = dog.age,
                                style = TextStyle(
                                    color = Color.Gray,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Medium,
                                    fontFamily = customFontFamily
                                )
                            )
                            Text(
                                text = dog.type,
                                style = TextStyle(
                                    color = MaterialTheme.colors.primary,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    fontFamily = customFontFamily
                                )
                            )
                        }

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(0.dp, Alignment.Start)
                        ) {

                            /*Icon(
                                imageVector = ImageVector.vectorResource(id = R.drawable.ic_outline_location_on_24),
                                tint = Color.Black,
                                contentDescription = null,
                                modifier = Modifier
                                    .size(18.dp)
                            )*/
                            Text(
                                text = "Çankaya",
                                style = TextStyle(
                                    color = Color.Gray,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Medium,
                                    fontFamily = customFontFamily
                                )
                            )
                            Text(
                                text = ", Ankara",
                                style = TextStyle(
                                    color = MaterialTheme.colors.onSurface,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    fontFamily = customFontFamily
                                )
                            )
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.weight(0.45f))
        }
    }
}

data class Dog(
    val id: Int,
    val name: String,
    val type: String,
    val male: Boolean,
    val imgResList: List<Int>,
    val age: String,
    val ownerImgRes: Int,
    val ownerName: String
)

val dogList: List<Dog> = listOf(
    Dog(
        1,
        "Niko",
        "Golden",
        true,
        listOf(R.drawable.golden1, R.drawable.golden2, R.drawable.golden3),
        "4 months old",
        R.drawable.owner1,
        "Marie Johnson"
    ),
    Dog(
        2,
        "Lulu",
        "Pug",
        false,
        listOf(R.drawable.pug2, R.drawable.pug3, R.drawable.pug4),
        "5 months old",
        R.drawable.owner4,
        "Greg Rodgers"
    ),
    Dog(
        3,
        "Ernie",
        "Pomeranian",
        true,
        listOf(R.drawable.pomeranian1),
        "3 months old",
        R.drawable.owner2,
        "Ad Soyad"
    ),
)
