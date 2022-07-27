package app.mozarty.movies.discover.ui.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.mozarty.movies.R
import app.mozarty.movies.ui.composable.TestTags

@Preview(showBackground = true)
@Composable
fun MovieListErrorState(){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 8.dp)
            .testTag(TestTags.EmptyList),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(R.drawable.ic_baseline_error_24),
            stringResource(id = R.string.body_error_list)
        )
        Text(
            text = stringResource(id = R.string.body_error_list),
            style = MaterialTheme.typography.bodyLarge
        )
    }
}


