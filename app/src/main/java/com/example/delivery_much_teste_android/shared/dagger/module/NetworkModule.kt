package com.example.delivery_much_teste_android.shared.dagger.module

import android.content.Context
import com.example.delivery_much_teste_android.R
import com.example.delivery_much_teste_android.shared.network.HeaderInterceptor
import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonParseException
import dagger.Module
import dagger.Provides
import dagger.Reusable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.threeten.bp.OffsetDateTime
import org.threeten.bp.format.DateTimeFormatter
import org.threeten.bp.format.DateTimeParseException
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.lang.reflect.Type
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.concurrent.TimeUnit

/**
 * @author Vinicius Gouveia on 26/05/2021
 */
@Module
object NetworkModule {

    private const val DEFAULT_TIMEOUT = 60L

    @Provides
    @Reusable
    @JvmStatic
    internal fun provideRetrofitInterface(
        context: Context
    ): Retrofit = Retrofit.Builder()
        .baseUrl(context.getString(R.string.api_url))
        .client(getHeader(context))
        .addConverterFactory(
            GsonConverterFactory.create(
                GsonBuilder()
                    .setLenient()
                    .registerTypeAdapter(Date::class.java, DateDeserializer())
                    .registerTypeAdapter(OffsetDateTime::class.java, OffsetDateTimeDeserializer())
                    .create()
            )
        )
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()

    private fun getHeader(
        context: Context
    ): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val builder = OkHttpClient.Builder()
            .readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
            .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(
                HeaderInterceptor(
                    context
                )
            )

        return builder.build()
    }

    private val DATE_FORMATS = listOf(
        "yyyy-MM-dd'T'HH:mm:ss.SSSSS",
        "yyyy-MM-dd'T'HH:mm:ss.SSSSSSZ",
        "yyyy-MM-dd'T'HH:mm:ss.SSSZ",
        "yyyy-MM-dd'T'HH:mm:ss",
        "yyyy-MM-dd' 'HH:mm:ss",
        "dd/MM/yyyy",
        "yyyy-MM-dd"
    )

    private class DateDeserializer : JsonDeserializer<Date> {

        override fun deserialize(
            json: JsonElement?,
            typeOfT: Type?,
            context: JsonDeserializationContext?
        ): Date {
            DATE_FORMATS.forEach {
                try {
                    return SimpleDateFormat(it, Locale("pt", "BR")).parse(json?.asString!!)!!
                } catch (exception: ParseException) {
                    // unused
                }
            }
            throw JsonParseException(
                "Unparseable date: ${json?.asString}.\nSupported formats:$DATE_FORMATS"
            )
        }
    }

    class OffsetDateTimeDeserializer : JsonDeserializer<OffsetDateTime> {

        override fun deserialize(
            json: JsonElement?,
            typeOfT: Type?,
            context: JsonDeserializationContext?
        ): OffsetDateTime {
            DateTimeFormatter.ISO_OFFSET_DATE_TIME.let { format ->
                try {
                    return OffsetDateTime.parse(json?.asString!!, format)
                        .withOffsetSameInstant(OffsetDateTime.now().offset)
                } catch (exception: DateTimeParseException) {
                    Timber.d("\n${json?.asString} could not be parsed to offset format $format")
                }
            }
            throw JsonParseException(
                "Unparseable date: ${json?.asString} \nSupported format: ${DateTimeFormatter.ISO_OFFSET_DATE_TIME}"
            )
        }
    }
}