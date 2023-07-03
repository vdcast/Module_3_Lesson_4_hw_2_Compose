package com.example.module_3_lesson_4_hw_2_compose

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.module_3_lesson_4_hw_2_compose.ui.theme.Module_3_Lesson_4_hw_2_ComposeTheme
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var retrofit = RetrofitClient.getClient("https://api.coindesk.com/")
            .create(API::class.java)

        setContent {
            Module_3_Lesson_4_hw_2_ComposeTheme {

                MyApp(retrofit = retrofit)

            }
        }
    }
}

@Composable
fun MyApp(retrofit: API) {
    val btcToUsd = remember { mutableStateOf("") }
    val btcToEur = remember { mutableStateOf("") }
    val btcToGbp = remember { mutableStateOf("") }

    retrofit.getCurrentPrice().enqueue(object : Callback<ResponseMain> {
        override fun onResponse(
            call: Call<ResponseMain>,
            response: Response<ResponseMain>
        ) {
            val btcToUsdRate = response.body()?.bpi?.usd?.rate.toString()
            btcToUsd.value = btcToUsdRate
            val btcToEurRate = response.body()?.bpi?.eur?.rate.toString()
            btcToEur.value = btcToEurRate
            val btcToGbpRate = response.body()?.bpi?.gbp?.rate.toString()
            btcToGbp.value = btcToGbpRate

        }

        override fun onFailure(call: Call<ResponseMain>, t: Throwable) {
            Log.d("MYLOG", "Some error in query")
        }

    })

    Image(
        painter = painterResource(id = R.drawable.bitcoin_moon_crypto),
        contentDescription = "Image background",
        contentScale = ContentScale.FillBounds
    )

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround
    ) {
        Text(
            text = stringResource(id = R.string.text_intro),
            textAlign = TextAlign.Center,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Monospace,
            color = Color.White,
            modifier = Modifier
                .padding(
                    top = dimensionResource(id = R.dimen.padding_large),
                    bottom = dimensionResource(id = R.dimen.padding_medium),
                    start = dimensionResource(id = R.dimen.padding_large),
                    end = dimensionResource(id = R.dimen.padding_large)
                )
        )

        Row(
            modifier = Modifier
                .padding(horizontal = dimensionResource(id = R.dimen.padding_small))
        ) {
            Text(
                text = stringResource(id = R.string.btc_to_usd),
                color = Color.White,
                fontFamily = FontFamily.Monospace
            )
            Spacer(modifier = Modifier.width(dimensionResource(R.dimen.padding_large)))
            Text(
                text = btcToUsd.value,
                textAlign = TextAlign.Center,
                fontStyle = FontStyle.Italic,
                color = Color.White,
                modifier = Modifier
            )
        }

        Row(
            modifier = Modifier
                .padding(horizontal = dimensionResource(id = R.dimen.padding_small))
        ) {
            Text(
                text = stringResource(id = R.string.btc_to_eur),
                color = Color.White,
                fontFamily = FontFamily.Monospace
            )
            Spacer(modifier = Modifier.width(dimensionResource(R.dimen.padding_large)))
            Text(
                text = btcToEur.value,
                textAlign = TextAlign.Center,
                fontStyle = FontStyle.Italic,
                color = Color.White,
                modifier = Modifier
            )
        }

        Row(
            modifier = Modifier
                .padding(horizontal = dimensionResource(id = R.dimen.padding_small))
        ) {
            Text(
                text = stringResource(id = R.string.btc_to_gbp),
                color = Color.White,
                fontFamily = FontFamily.Monospace
            )
            Spacer(modifier = Modifier.width(dimensionResource(R.dimen.padding_large)))
            Text(
                text = btcToGbp.value,
                textAlign = TextAlign.Center,
                fontStyle = FontStyle.Italic,
                color = Color.White,
                modifier = Modifier
            )
        }

        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.spacer)))

        Text(
            text = stringResource(id = R.string.text_bottom),
            textAlign = TextAlign.Center,
            fontFamily = FontFamily.Monospace,
            color = Color.White,
            modifier = Modifier
                .padding(horizontal = dimensionResource(id = R.dimen.padding_large))
        )
        Button(
            modifier = Modifier
                .padding(bottom = dimensionResource(id = R.dimen.padding_medium)),
            onClick = {
                retrofit.getCurrentPrice().enqueue(object : Callback<ResponseMain> {
                    override fun onResponse(
                        call: Call<ResponseMain>,
                        response: Response<ResponseMain>
                    ) {
                        val btcToUsdRate = response.body()?.bpi?.usd?.rate.toString()
                        btcToUsd.value = btcToUsdRate
                        val btcToEurRate = response.body()?.bpi?.eur?.rate.toString()
                        btcToEur.value = btcToEurRate
                        val btcToGbpRate = response.body()?.bpi?.gbp?.rate.toString()
                        btcToGbp.value = btcToGbpRate
                    }

                    override fun onFailure(call: Call<ResponseMain>, t: Throwable) {
                        Log.d("MYLOG", "Some error in getRandomIdea()")
                    }

                })
            }
        ) {
            Text(
                text = stringResource(id = R.string.button_refresh),
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.SemiBold
            )
        }

    }
}