using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;


public class contador : MonoBehaviour
{
    public Text Texto;//lo pasamos al objeto texto en el inspector
    public int cont;//le pasamos por parametros 

    private void Start()
    {

        cont = 0;
    }


    private void Update()
    {

        Texto.text = "Colisiones " + cont;


    }

}
