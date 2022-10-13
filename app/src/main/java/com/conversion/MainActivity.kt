package mx.edu.utez.conversion
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import mx.edu.utez.conversion.databinding.ActivityMainBinding
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val formatDecimalFormat = DecimalFormat("#.00")

        val Editconversion: Spinner = binding.conversion
        ArrayAdapter.createFromResource(
            this,
            R.array.conversion,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            Editconversion.adapter = adapter
        }

        val Editconversion2: Spinner = binding.conversion2
        ArrayAdapter.createFromResource(
            this,
            R.array.conversion2,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            Editconversion2.adapter = adapter
        }

        val btnconversion = binding.btnconversion
        btnconversion.setOnClickListener {
            val conversionString = Editconversion.selectedItem.toString()
            val conversionString2 = Editconversion2.selectedItem.toString()
            val dinero = binding.dinero.text.toString()
            var result = binding.result
            if (dinero.isNotEmpty()){
                when (conversionString){
                    in "Dolar" ->{ //de dolares a
                        if (conversionString2 == "Euro") {
                            var conver = 1.02 * dinero.toDouble()
                            conver=formatDecimalFormat.format(conver).toDouble()
                            result.text = "La conversion es: $conver"
                        }else if (conversionString2 == "Pesos") {
                            var conver = dinero.toDouble() * 19.97
                            result.text = "La conversion es: $conver"
                        }else{
                            result.text = "La conversion es: $dinero"
                        }

                    }
                    in "Euro" ->{ //vde euros a
                        if (conversionString2 == "Dolar") {
                            var conver = 0.98 * dinero.toDouble()
                            result.text = "La conversion es: $conver"
                        }else if (conversionString2 == "Pesos") {
                            var conver = dinero.toDouble() * 19.56
                            result.text = "La conversion es: $conver"
                        }else{
                            result.text = "La conversion es: $dinero"
                        }

                    }
                    in "Pesos" ->{ //de pesos a
                        if (conversionString2 == "Euro") {
                            var conver = 0.051 * dinero.toDouble()
                            result.text = "La conversion es: $conver"
                        }else if (conversionString2 == "Dolar") {
                            var conver = dinero.toDouble() * 0.050
                            result.text = "La conversion es: $conver"
                        }else{
                            result.text = "La conversion es: $dinero"
                        }
                    }
                }
            }else{
                Toast.makeText(this@MainActivity,"llena primero los campos",Toast.LENGTH_SHORT).show()
            }
        }
    }
}