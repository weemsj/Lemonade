package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
                // A surface container using the 'background' color from the theme

                LemonadeApp()

            }
        }
    }
}

@Composable
fun LemonadeImagesAndSteps(modifier: Modifier = Modifier) {
    Banner()
    var step by remember {
        mutableStateOf(1)
    }
    var squeeze by remember {
        mutableStateOf(0)
    }
    val img = imageFinder(result = step)
    val pad = 16.dp
    val instructions = textFinder(result = step)

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
    )
    {
        Surface(color = colorResource(id = R.color.lightermint),
            shape = RoundedCornerShape(50.dp)
        ) {
            Image(
                painter = img,
                contentDescription = stringResource(id = R.string.lemon_tree_content_description),
                contentScale = ContentScale.Inside,
                modifier = modifier
                    .clickable(onClick = {
                        if (step in 1..3) {
                            if(step == 1){
                                squeeze = (2..4).random()
                                step++
                            }
                            if(step == 2){
                                squeeze--
                                if(squeeze == 0){
                                    step++
                                }
                            }
                            else {
                                step++
                            }
                        } else {
                            step = 1
                        }
                    }
                    )
                    .size(250.dp)
                    ,



                )
        }
        Spacer(modifier = modifier.size(pad))
        Text(
            text = instructions,
            fontSize = 18.sp,
        )
    }
}
@Composable
fun imageFinder(result:Int): Painter {
    var img = when (result) {
        1 -> painterResource(id = R.drawable.lemon_tree)
        2 -> painterResource(id = R.drawable.lemon_squeeze)
        3 -> painterResource(id = R.drawable.lemon_drink)
        else -> painterResource(id = R.drawable.lemon_restart)
    }
    return img
}

@Composable
fun textFinder(result:Int): String {
    var instructions = when (result){
        1 -> stringResource(id = R.string.instruction_1)
        2 -> stringResource(id = R.string.instruction_2)
        3 -> stringResource(id = R.string.instruction_3)
        else -> stringResource(id = R.string.instruction_4)
    }
    return instructions
}

fun ClickCounter(){
}


@Composable
fun Banner(modifier: Modifier = Modifier){
    Column(verticalArrangement = Arrangement.Top,
            modifier = modifier
                .background(color = colorResource(id = R.color.yellow))
                .fillMaxWidth()
                .height(75.dp)
                .padding(top = 25.dp)
    ) {
        Text(
            modifier = modifier
                .fillMaxWidth(),
            text = stringResource(id = R.string.app_name),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
            )
        }

    }

@Preview(showBackground = true)
@Composable
fun LemonadeApp() {
    LemonadeImagesAndSteps()


}