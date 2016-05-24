using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Text;
using System.Windows.Forms;
using System.Runtime.InteropServices;

namespace BioStarCSharp
{
    public partial class BioStar : Form
    {
        public const int MAX_DEVICE = 128;

        private int m_Handle = -1;

        private int m_NumOfDevice = 0;
        private uint[] m_DeviceID;
        private int[] m_DeviceType;
        private uint[] m_DeviceAddr;

        private int m_NumOfConnectedDevice = 0;
        private int[] m_ConnectedDeviceHandle;
        private uint[] m_ConnectedDeviceID;
        private int[] m_ConnectedDeviceType;
        private uint[] m_ConnectedDeviceAddr;

        public BioStar()
        {
            InitializeComponent();

            m_DeviceID = new uint[MAX_DEVICE];
            m_DeviceType = new int[MAX_DEVICE];
            m_DeviceAddr = new uint[MAX_DEVICE];

            m_ConnectedDeviceHandle = new int[MAX_DEVICE];
            m_ConnectedDeviceID = new uint[MAX_DEVICE];
            m_ConnectedDeviceType = new int[MAX_DEVICE];
            m_ConnectedDeviceAddr = new uint[MAX_DEVICE];
        }

        private void BioStar_Load(object sender, EventArgs e)
        {
            int result = BSSDK.BS_InitSDK();

            if (result != BSSDK.BS_SUCCESS)
            {
                MessageBox.Show("Cannot initialize the SDK", "Error");
                return;
            }

            result = BSSDK.BS_OpenInternalUDP(ref m_Handle);

            if (result != BSSDK.BS_SUCCESS)
            {
                MessageBox.Show("Cannot open internal UDP socket", "Error");
                return;
            }
        }

        private void searchButton_Click(object sender, EventArgs e)
        {
            deviceList.Items.Clear();

            Cursor.Current = Cursors.WaitCursor;

            int result;

            try
            {
                result = BSSDK.BS_SearchDeviceInLAN(m_Handle, ref m_NumOfDevice, m_DeviceID, m_DeviceType, m_DeviceAddr);
            }
            finally
            {
                Cursor.Current = Cursors.Default;
            }

            if (result != BSSDK.BS_SUCCESS)
            {
                MessageBox.Show("Cannot find any device", "Error");
                return;
            }

            for (int i = 0; i < m_NumOfDevice; i++)
            {
                string device = "";

                if (m_DeviceType[i] == BSSDK.BS_DEVICE_BIOSTATION)
                {
                    device += "BioStation ";
                }
                else if (m_DeviceType[i] == BSSDK.BS_DEVICE_BEPLUS)
                {
                    device += "BioEntry Plus ";
                }
                else
                {
                    device += "BioLite Net ";
                }

                device += (m_DeviceAddr[i] & 0xff) + ".";
                device += ((m_DeviceAddr[i] >> 8) & 0xff) + ".";
                device += ((m_DeviceAddr[i] >> 16) & 0xff) + ".";
                device += ((m_DeviceAddr[i] >> 24) & 0xff);

                device += "(" + m_DeviceID[i] + ")";

                deviceList.Items.Add(device);
            }

        }

        private void closeButton_Click(object sender, EventArgs e)
        {
            Dispose();
        }

        private void networkConfigButton_Click(object sender, EventArgs e)
        {
            if (deviceList.SelectedIndex < 0)
            {
                MessageBox.Show("Select a device first", "Error");
                return;
            }

            NetworkConfig networkConfig = new NetworkConfig();

            networkConfig.SetDevice(m_Handle, m_DeviceID[deviceList.SelectedIndex], m_DeviceAddr[deviceList.SelectedIndex], m_DeviceType[deviceList.SelectedIndex], this);

            networkConfig.Show();
        }


        public void AddConnectedDevice(uint deviceID, int deviceType, uint deviceAddr, int deviceHandle)
        {
            string device = "";

            if (deviceType == BSSDK.BS_DEVICE_BIOSTATION)
            {
                device += "BioStation ";
            }
            else if (deviceType == BSSDK.BS_DEVICE_BEPLUS)
            {
                device += "BioEntry Plus ";
            }
            else
            {
                device += "BioLite Net ";
            }

            device += (deviceAddr & 0xff) + ".";
            device += ((deviceAddr >> 8) & 0xff) + ".";
            device += ((deviceAddr >> 16) & 0xff) + ".";
            device += ((deviceAddr >> 24) & 0xff);
            device += "(" + deviceID + ")";

            for (int i = 0; i < m_NumOfConnectedDevice; i++)
            {
                if (m_ConnectedDeviceID[i] == deviceID)
                {
                    m_ConnectedDeviceType[i] = deviceType;
                    m_ConnectedDeviceAddr[i] = deviceAddr;
                    m_ConnectedDeviceHandle[i] = deviceHandle;

                    connectedDeviceList.Items.RemoveAt(i);
                    connectedDeviceList.Items.Insert(i, device);

                    return;
                }
            }

            m_ConnectedDeviceID[m_NumOfConnectedDevice] = deviceID;
            m_ConnectedDeviceType[m_NumOfConnectedDevice] = deviceType;
            m_ConnectedDeviceAddr[m_NumOfConnectedDevice] = deviceAddr;
            m_ConnectedDeviceHandle[m_NumOfConnectedDevice++] = deviceHandle;

            connectedDeviceList.Items.Add(device);
        }

        private void clearButton_Click(object sender, EventArgs e)
        {
            for (int i = 0; i < m_NumOfConnectedDevice; i++)
            {
                BSSDK.BS_CloseSocket(m_ConnectedDeviceHandle[i]);
            }

            m_NumOfConnectedDevice = 0;

            connectedDeviceList.Items.Clear();
        }

        private void timeTest_Click(object sender, EventArgs e)
        {
            if (connectedDeviceList.SelectedIndex < 0)
            {
                MessageBox.Show("Select a connected device first", "Error");
                return;
            }

            BSSDK.BS_SetDeviceID(m_ConnectedDeviceHandle[connectedDeviceList.SelectedIndex],
                m_ConnectedDeviceID[connectedDeviceList.SelectedIndex], m_ConnectedDeviceType[connectedDeviceList.SelectedIndex]);

            SetupTime setupTime = new SetupTime();

            setupTime.setHandle(m_ConnectedDeviceHandle[connectedDeviceList.SelectedIndex]);

            setupTime.Show();
        }

        private void logTest_Click(object sender, EventArgs e)
        {
            if (connectedDeviceList.SelectedIndex < 0)
            {
                MessageBox.Show("Select a connected device first", "Error");
                return;
            }

            BSSDK.BS_SetDeviceID(m_ConnectedDeviceHandle[connectedDeviceList.SelectedIndex],
                m_ConnectedDeviceID[connectedDeviceList.SelectedIndex], m_ConnectedDeviceType[connectedDeviceList.SelectedIndex]);

            LogManagement logTest = new LogManagement();

            logTest.SetDevice(m_ConnectedDeviceHandle[connectedDeviceList.SelectedIndex],
                m_ConnectedDeviceID[connectedDeviceList.SelectedIndex], m_ConnectedDeviceType[connectedDeviceList.SelectedIndex]);

            logTest.Show();
        }

        private void userTest_Click(object sender, EventArgs e)
        {
            if (connectedDeviceList.SelectedIndex < 0)
            {
                MessageBox.Show("Select a connected device first", "Error");
                return;
            }

            BSSDK.BS_SetDeviceID(m_ConnectedDeviceHandle[connectedDeviceList.SelectedIndex],
                m_ConnectedDeviceID[connectedDeviceList.SelectedIndex], m_ConnectedDeviceType[connectedDeviceList.SelectedIndex]);

            UserManagement userTest = new UserManagement();

            userTest.SetDevice(m_ConnectedDeviceHandle[connectedDeviceList.SelectedIndex],
                m_ConnectedDeviceID[connectedDeviceList.SelectedIndex], m_ConnectedDeviceType[connectedDeviceList.SelectedIndex]);

            userTest.Show();

        }
    }
}