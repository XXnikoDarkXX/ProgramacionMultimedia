using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class incremento : MonoBehaviour
{

    // Start is called before the first frame update
    void Start()
    {

    }

    // Update is called once per frame
    void Update()
    {

    }


    private void OnCollisionEnter(Collision collision)
    {

        if (collision.gameObject.tag == "enemigo")
        {



            GameObject.Find("Texto").GetComponent<contador>().cont++;

            //String conexionString = "URI=file:" + Application.dataPath + "/Plugins/bdUnity.db";
            // IDbConnection conexion = (IDbConnection)new SqliteConnection(conexionString);

            // conexion.Open();
            // IDbCommand consulta = conexion.CreateCommand();
            // consulta.CommandText = "update datos set toques=" + GameObject.Find("Texto").GetComponent<contador>().cont++;
            // consulta.ExecuteNonQuery();

            // consulta.Dispose();
            // consulta = null;
            //  conexion.Close();
            //  conexion = null;

        }

    }
}
