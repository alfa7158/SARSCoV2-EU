import android.annotation.SuppressLint
import com.example.covideu.API.CovidEu_API
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception

private val BASE_URL = "https://vaccovid-coronavirus-vaccine-and-treatment-tracker.p.rapidapi.com"

class ApiRepositoryCovidData {


    private val retrofitService = Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(
        GsonConverterFactory.create()).build()
    private val
            retrofitApi = retrofitService.create(CovidEu_API::class.java)
    /**below are the functions for the covid data for countries and the world*/
    suspend fun getStatisticalData() = retrofitApi.getCovid19StatisticalData()

    suspend fun getDataForCountriesWithCovid19() = retrofitApi.getDataForCountriesWithCovid19()

    suspend fun getCovidDataForAsia() = retrofitApi.getCovidDataForAsia()

    suspend fun getCovidDataForAfrica() = retrofitApi.getCovidDataForAfrica()

    suspend fun getCovidDataForEurope() = retrofitApi.getCovidDataForEurope()

    suspend fun getCovidDataForSouthAmerican() = retrofitApi.getCovidDataForSouthAmerican()

    suspend fun getCovidDataForNorthAmerican() = retrofitApi.getCovidDataForNorthAmerican()

    suspend fun getCovidDataForAustraliaAndOceania() = retrofitApi.getCovidDataForAustraliaAndOceania()

    suspend fun getWorldData() = retrofitApi.getWorldCovidData()

    /**below are the functions for the covid data for treatment and vaccine*/

    suspend fun getAllTreatmentData() = retrofitApi.getAllTreatmentData()

    suspend fun getAllVaccinesData() = retrofitApi.getAllVaccinesData()

    suspend fun getAllClinicalTreatment() = retrofitApi.getAllClinicalTreatment()

    suspend fun getAllApprovedFDATreatmentData() = retrofitApi.getAllApprovedFDATreatmentData()

    suspend fun getAll_FDA_VaccinesApproved() = retrofitApi.getAllApprovedFDAVaccinesData()

    suspend fun getPhaseOne() = retrofitApi.getPhaseOne()

    suspend fun getPhaseTwo() = retrofitApi.getPhaseTwo()

    suspend fun getPhaseThree() = retrofitApi.getPhaseThree()

    suspend fun getPhaseFour() = retrofitApi.getPhaseFour()


    /**below are the functions for the covid and public health news*/

    suspend fun getAllCovid19News(pageC:String) = retrofitApi.getAllCovid19News(pageC )

    suspend fun getAllHealthNews(pageH:String) = retrofitApi.getAllHealthNews(pageH)

    suspend fun getAllVaccineNews(pageV:String) = retrofitApi.getAllVaccineNews(pageV)


    companion object{
        @SuppressLint("StaticFieldLeak")
        private var instance:ApiRepositoryCovidData?=null

        fun init(){
            if(instance==null){
                instance = ApiRepositoryCovidData()
            }

        }

        fun get():ApiRepositoryCovidData{
            return instance?:throw Exception("Api must be initialized")

        }
    }


}