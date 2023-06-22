package com.valen.kwonnect;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface APIService {

    @FormUrlEncoded
    @POST("auth/login")
    Call<ValueData<User>> login(@Field("username") String username,
                                @Field("password") String password);

    @FormUrlEncoded
    @POST("auth/register")
    Call<ValueData<User>> register(@Field("username") String username,
                                   @Field("password") String password);

    @GET("anggota")
    Call<ValueData<List<AnggotaModel>>> getAnggota();

    @FormUrlEncoded
    @POST("anggota")
    Call<ValueNoData> addAnggota(@Field("user_id") String user_id,
                                 @Field("foto") String foto,
                                 @Field("email") String email,
                                 @Field("nama") String nama,
                                 @Field("npm") String npm,
                                 @Field("prodi") String prodi,
                                 @Field("sabuk") String sabuk,
                                 @Field("tempat_lahir") String tempat_lahir,
                                 @Field("tanggal_lahir") String tanggal_lahir,
                                 @Field("tinggi_badan") String tinggi_badan,
                                 @Field("berat_badan") String berat_badan,
                                 @Field("nomor_whatsapp") String nomor_whatsapp);

    @FormUrlEncoded
    @PUT("post")
    Call<ValueNoData> updateAnggota(@Field("id") String id,
                                    @Field("foto") String foto,
                                    @Field("email") String email,
                                    @Field("nama") String nama,
                                    @Field("npm") String npm,
                                    @Field("prodi") String prodi,
                                    @Field("sabuk") String sabuk,
                                    @Field("tempat_lahir") String tempat_lahir,
                                    @Field("tanggal_lahir") String tanggal_lahir,
                                    @Field("tinggi_badan") String tinggi_badan,
                                    @Field("berat_badan") String berat_badan);

    @DELETE("post/{id}")
    Call<ValueNoData> deleteAnggota(@Path("id") String id);
}
