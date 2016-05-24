using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Text;
using System.Windows.Forms;
using System.Runtime.InteropServices;
using System.Net;

namespace BioStarCSharp
{
    public partial class NetworkConfig : Form
    {
        private int m_Handle;

        private uint m_DeviceID;
        private uint m_DeviceAddr;
        private int m_DeviceType;

        BSSDK.BESysInfoData m_SysInfoBEPlus;
        BSSDK.BEConfigData m_ConfigBEPlus;

        BSSDK.BESysInfoDataBLN m_SysInfoBLN;
        BSSDK.BEConfigDataBLN m_ConfigBLN;

        BSSDK.BSSysInfoConfig m_SysInfoBST;
        BSSDK.BSIPConfig m_ConfigBST;        

        BioStar m_MainForm;

        public NetworkConfig()
        {
            InitializeComponent();
        }

        private void NetworkConfig_Load(object sender, EventArgs e)
        {
            deviceInfo.Text = (m_DeviceAddr & 0xff) + ".";
            deviceInfo.Text += ((m_DeviceAddr >> 8) & 0xff) + ".";
            deviceInfo.Text += ((m_DeviceAddr >> 16) & 0xff) + ".";
            deviceInfo.Text += ((m_DeviceAddr >> 24) & 0xff);
            deviceInfo.Text += " (" + m_DeviceID + ")";

            if (ReadSysInfo())
            {
                ReadConfig();
            }
        }

        public void SetDevice(int handle, uint deviceID, uint deviceAddr, int deviceType, BioStar mainForm)
        {
            m_Handle = handle;
            m_DeviceID = deviceID;
            m_DeviceAddr = deviceAddr;
            m_DeviceType = deviceType;

            m_MainForm = mainForm;
        }

        private bool ReadSysInfo()
        {
            int configSize = 0;

            Cursor.Current = Cursors.WaitCursor;

            if (m_DeviceType == BSSDK.BS_DEVICE_BEPLUS)
            {
                IntPtr sysInfo = Marshal.AllocHGlobal(Marshal.SizeOf(m_SysInfoBEPlus));

                int result = BSSDK.BS_ReadConfigUDP(m_Handle, m_DeviceAddr, m_DeviceID, BSSDK.BEPLUS_CONFIG_SYS_INFO, ref configSize, sysInfo);

                Cursor.Current = Cursors.Default;

                if (result != BSSDK.BS_SUCCESS)
                {
                    MessageBox.Show("Cannot read the sys info", "Error");

                    Marshal.FreeHGlobal(sysInfo);
                    return false;
                }

                m_SysInfoBEPlus = (BSSDK.BESysInfoData)Marshal.PtrToStructure(sysInfo, typeof(BSSDK.BESysInfoData));

                Marshal.FreeHGlobal(sysInfo);

                deviceID.Text = String.Format("{0}", m_SysInfoBEPlus.ID);

                MAC.Text = "";

                int i = 0;
                for (i = 0; i < 5; i++)
                {
                    MAC.Text += m_SysInfoBEPlus.macAddr[i].ToString("X2") + ":";
                }
                MAC.Text += m_SysInfoBEPlus.macAddr[i].ToString("X2");

                FWVersion.Text = Encoding.ASCII.GetString(m_SysInfoBEPlus.firmwareVer);
            }
            else if (m_DeviceType == BSSDK.BS_DEVICE_BIOLITE)
            {
                IntPtr sysInfo = Marshal.AllocHGlobal(Marshal.SizeOf(m_SysInfoBLN));

                int result = BSSDK.BS_ReadConfigUDP(m_Handle, m_DeviceAddr, m_DeviceID, BSSDK.BLN_CONFIG_SYS_INFO, ref configSize, sysInfo);

                Cursor.Current = Cursors.Default;

                if (result != BSSDK.BS_SUCCESS)
                {
                    MessageBox.Show("Cannot read the sys info", "Error");

                    Marshal.FreeHGlobal(sysInfo);
                    return false;
                }

                m_SysInfoBLN = (BSSDK.BESysInfoDataBLN)Marshal.PtrToStructure(sysInfo, typeof(BSSDK.BESysInfoDataBLN));

                Marshal.FreeHGlobal(sysInfo);

                deviceID.Text = String.Format("{0}", m_SysInfoBLN.ID);

                MAC.Text = "";

                int i = 0;
                for (i = 0; i < 5; i++)
                {
                    MAC.Text += m_SysInfoBLN.macAddr[i].ToString("X2") + ":";
                }
                MAC.Text += m_SysInfoBLN.macAddr[i].ToString("X2");

                FWVersion.Text = Encoding.ASCII.GetString(m_SysInfoBLN.firmwareVer);
            }
            else if (m_DeviceType == BSSDK.BS_DEVICE_BIOSTATION)
            {
                IntPtr sysInfo = Marshal.AllocHGlobal(Marshal.SizeOf(m_SysInfoBST));

                int result = BSSDK.BS_ReadConfigUDP(m_Handle, m_DeviceAddr, m_DeviceID, BSSDK.BS_CONFIG_SYS_INFO, ref configSize, sysInfo);

                Cursor.Current = Cursors.Default;

                if (result != BSSDK.BS_SUCCESS)
                {
                    MessageBox.Show("Cannot read the sys info", "Error");

                    Marshal.FreeHGlobal(sysInfo);
                    return false;
                }

                m_SysInfoBST = (BSSDK.BSSysInfoConfig)Marshal.PtrToStructure(sysInfo, typeof(BSSDK.BSSysInfoConfig));

                Marshal.FreeHGlobal(sysInfo);

                deviceID.Text = String.Format("{0}", m_SysInfoBST.ID);

                MAC.Text = Encoding.ASCII.GetString(m_SysInfoBST.macAddr);
                FWVersion.Text = Encoding.ASCII.GetString(m_SysInfoBST.firmwareVer);
            }


            return true;
        }

        private bool ReadConfig()
        {
            int configSize = 0;

            Cursor.Current = Cursors.WaitCursor;

            if (m_DeviceType == BSSDK.BS_DEVICE_BEPLUS)
            {
                IntPtr config = Marshal.AllocHGlobal(Marshal.SizeOf(m_ConfigBEPlus));

                int result = BSSDK.BS_ReadConfigUDP(m_Handle, m_DeviceAddr, m_DeviceID, BSSDK.BEPLUS_CONFIG, ref configSize, config);

                Cursor.Current = Cursors.Default;

                if (result != BSSDK.BS_SUCCESS)
                {
                    MessageBox.Show("Cannot read the configuration", "Error");

                    Marshal.FreeHGlobal(config);
                    return false;
                }

                m_ConfigBEPlus = (BSSDK.BEConfigData)Marshal.PtrToStructure(config, typeof(BSSDK.BEConfigData));

                Marshal.FreeHGlobal(config);

                DHCP.Checked = m_ConfigBEPlus.useDHCP;
                useServer.Checked = m_ConfigBEPlus.useServer;
                synchTime.Checked = m_ConfigBEPlus.synchTime;

                IPAddress addr = new IPAddress(m_ConfigBEPlus.ipAddr);
                ipAddr.Text = addr.ToString();

                addr.Address = m_ConfigBEPlus.gateway;
                gateway.Text = addr.ToString();

                addr.Address = m_ConfigBEPlus.subnetMask;
                subnetMask.Text = addr.ToString();

                addr.Address = m_ConfigBEPlus.serverIpAddr;
                serverIP.Text = addr.ToString();

                port.Text = m_ConfigBEPlus.port.ToString();

                if (DHCP.Checked)
                {
                    ipAddr.Enabled = false;
                    gateway.Enabled = false;
                    subnetMask.Enabled = false;
                }
                else
                {
                    ipAddr.Enabled = true;
                    gateway.Enabled = true;
                    subnetMask.Enabled = true;
                }
            }
            else if (m_DeviceType == BSSDK.BS_DEVICE_BIOLITE)
            {
                IntPtr config = Marshal.AllocHGlobal(Marshal.SizeOf(m_ConfigBLN));

                int result = BSSDK.BS_ReadConfigUDP(m_Handle, m_DeviceAddr, m_DeviceID, BSSDK.BLN_CONFIG, ref configSize, config);

                Cursor.Current = Cursors.Default;

                if (result != BSSDK.BS_SUCCESS)
                {
                    MessageBox.Show("Cannot read the configuration", "Error");

                    Marshal.FreeHGlobal(config);
                    return false;
                }

                m_ConfigBLN = (BSSDK.BEConfigDataBLN)Marshal.PtrToStructure(config, typeof(BSSDK.BEConfigDataBLN));

                Marshal.FreeHGlobal(config);

                DHCP.Checked = m_ConfigBLN.useDHCP;
                useServer.Checked = m_ConfigBLN.useServer;
                synchTime.Checked = m_ConfigBLN.synchTime;

                IPAddress addr = new IPAddress(m_ConfigBLN.ipAddr);
                ipAddr.Text = addr.ToString();

                addr.Address = m_ConfigBLN.gateway;
                gateway.Text = addr.ToString();

                addr.Address = m_ConfigBLN.subnetMask;
                subnetMask.Text = addr.ToString();

                addr.Address = m_ConfigBLN.serverIpAddr;
                serverIP.Text = addr.ToString();

                port.Text = m_ConfigBLN.port.ToString();

                if (DHCP.Checked)
                {
                    ipAddr.Enabled = false;
                    gateway.Enabled = false;
                    subnetMask.Enabled = false;
                }
                else
                {
                    ipAddr.Enabled = true;
                    gateway.Enabled = true;
                    subnetMask.Enabled = true;
                }
            }
            else if (m_DeviceType == BSSDK.BS_DEVICE_BIOSTATION)
            {
                IntPtr config = Marshal.AllocHGlobal(Marshal.SizeOf(m_ConfigBST));

                int result = BSSDK.BS_ReadConfigUDP(m_Handle, m_DeviceAddr, m_DeviceID, BSSDK.BS_CONFIG_TCPIP, ref configSize, config);

                Cursor.Current = Cursors.Default;

                if (result != BSSDK.BS_SUCCESS)
                {
                    MessageBox.Show("Cannot read the configuration", "Error");

                    Marshal.FreeHGlobal(config);
                    return false;
                }

                m_ConfigBST = (BSSDK.BSIPConfig)Marshal.PtrToStructure(config, typeof(BSSDK.BSIPConfig));

                Marshal.FreeHGlobal(config);

                DHCP.Checked = m_ConfigBST.useDHCP;
                useServer.Checked = m_ConfigBST.useServer;
                synchTime.Checked = m_ConfigBST.syncTimeWithServer;

                ipAddr.Text = Encoding.ASCII.GetString(m_ConfigBST.ipAddr);
                gateway.Text = Encoding.ASCII.GetString(m_ConfigBST.gateway);
                subnetMask.Text = Encoding.ASCII.GetString(m_ConfigBST.subnetMask);
                serverIP.Text = Encoding.ASCII.GetString(m_ConfigBST.serverIP);

                if (m_ConfigBST.useServer)
                {
                    port.Text = m_ConfigBST.serverPort.ToString();
                }
                else
                {
                    port.Text = m_ConfigBST.port.ToString();
                }

                if (DHCP.Checked)
                {
                    ipAddr.Enabled = false;
                    gateway.Enabled = false;
                    subnetMask.Enabled = false;
                }
                else
                {
                    ipAddr.Enabled = true;
                    gateway.Enabled = true;
                    subnetMask.Enabled = true;
                }
            }

            return true;
        }

        private void closeButton_Click(object sender, EventArgs e)
        {
            Dispose();
        }

        private void refreshButton_Click(object sender, EventArgs e)
        {
            ReadSysInfo();
        }

        private void DHCP_CheckedChanged(object sender, EventArgs e)
        {
            if (DHCP.Checked)
            {
                ipAddr.Enabled = false;
                gateway.Enabled = false;
                subnetMask.Enabled = false;
            }
            else
            {
                ipAddr.Enabled = true;
                gateway.Enabled = true;
                subnetMask.Enabled = true;
            }
        }

        private void refreshConfig_Click(object sender, EventArgs e)
        {
            ReadConfig();
        }

        private void writeConfig_Click(object sender, EventArgs e)
        {
            if (m_DeviceType == BSSDK.BS_DEVICE_BEPLUS)
            {
                m_ConfigBEPlus.useDHCP = DHCP.Checked;
                m_ConfigBEPlus.useServer = useServer.Checked;
                m_ConfigBEPlus.synchTime = synchTime.Checked;

                IPAddress addr = IPAddress.Parse(ipAddr.Text);
                m_ConfigBEPlus.ipAddr = (uint)addr.Address;

                addr = IPAddress.Parse(gateway.Text);
                m_ConfigBEPlus.gateway = (uint)addr.Address;

                addr = IPAddress.Parse(subnetMask.Text);
                m_ConfigBEPlus.subnetMask = (uint)addr.Address;

                addr = IPAddress.Parse(serverIP.Text);
                m_ConfigBEPlus.serverIpAddr = (uint)addr.Address;

                m_ConfigBEPlus.port = Int32.Parse(port.Text);

                int configSize = Marshal.SizeOf(m_ConfigBEPlus);
                IntPtr config = Marshal.AllocHGlobal(configSize);

                Marshal.StructureToPtr(m_ConfigBEPlus, config, true);

                Cursor.Current = Cursors.WaitCursor;

                int result = BSSDK.BS_WriteConfigUDP(m_Handle, m_DeviceAddr, m_DeviceID, BSSDK.BEPLUS_CONFIG, configSize, config);

                Marshal.FreeHGlobal(config);

                Cursor.Current = Cursors.Default;

                if (result != BSSDK.BS_SUCCESS)
                {
                    MessageBox.Show("Cannot write the configuration", "Error");
                }
            }
            else if (m_DeviceType == BSSDK.BS_DEVICE_BIOLITE)
            {
                m_ConfigBLN.useDHCP = DHCP.Checked;
                m_ConfigBLN.useServer = useServer.Checked;
                m_ConfigBLN.synchTime = synchTime.Checked;

                IPAddress addr = IPAddress.Parse(ipAddr.Text);
                m_ConfigBLN.ipAddr = (uint)addr.Address;

                addr = IPAddress.Parse(gateway.Text);
                m_ConfigBLN.gateway = (uint)addr.Address;

                addr = IPAddress.Parse(subnetMask.Text);
                m_ConfigBLN.subnetMask = (uint)addr.Address;

                addr = IPAddress.Parse(serverIP.Text);
                m_ConfigBLN.serverIpAddr = (uint)addr.Address;

                m_ConfigBLN.port = Int32.Parse(port.Text);

                int configSize = Marshal.SizeOf(m_ConfigBLN);
                IntPtr config = Marshal.AllocHGlobal(configSize);

                Marshal.StructureToPtr(m_ConfigBLN, config, true);

                Cursor.Current = Cursors.WaitCursor;

                int result = BSSDK.BS_WriteConfigUDP(m_Handle, m_DeviceAddr, m_DeviceID, BSSDK.BLN_CONFIG, configSize, config);

                Marshal.FreeHGlobal(config);

                Cursor.Current = Cursors.Default;

                if (result != BSSDK.BS_SUCCESS)
                {
                    MessageBox.Show("Cannot write the configuration", "Error");
                }
            }
            else if (m_DeviceType == BSSDK.BS_DEVICE_BIOSTATION)
            {
                m_ConfigBST.useDHCP = DHCP.Checked;
                m_ConfigBST.useServer = useServer.Checked;
                m_ConfigBST.syncTimeWithServer = synchTime.Checked;

                byte[] addr = Encoding.ASCII.GetBytes( ipAddr.Text );
                Buffer.BlockCopy( addr, 0, m_ConfigBST.ipAddr, 0, addr.Length );

                addr = Encoding.ASCII.GetBytes(gateway.Text);
                Buffer.BlockCopy(addr, 0, m_ConfigBST.gateway, 0, addr.Length);

                addr = Encoding.ASCII.GetBytes(subnetMask.Text);
                Buffer.BlockCopy(addr, 0, m_ConfigBST.subnetMask, 0, addr.Length);

                addr = Encoding.ASCII.GetBytes(serverIP.Text);
                Buffer.BlockCopy(addr, 0, m_ConfigBST.serverIP, 0, addr.Length);

                if (useServer.Checked)
                {
                    m_ConfigBST.serverPort = UInt32.Parse(port.Text);
                }
                else
                {
                    m_ConfigBST.port = UInt32.Parse(port.Text);
                }

                int configSize = Marshal.SizeOf(m_ConfigBST);
                IntPtr config = Marshal.AllocHGlobal(configSize);

                Marshal.StructureToPtr(m_ConfigBST, config, true);

                Cursor.Current = Cursors.WaitCursor;

                int result = BSSDK.BS_WriteConfigUDP(m_Handle, m_DeviceAddr, m_DeviceID, BSSDK.BS_CONFIG_TCPIP, configSize, config);

                Marshal.FreeHGlobal(config);

                Cursor.Current = Cursors.Default;

                if (result != BSSDK.BS_SUCCESS)
                {
                    MessageBox.Show("Cannot write the configuration", "Error");
                }
            }
        }

        private void connect_Click(object sender, EventArgs e)
        {
            IPAddress addr = new IPAddress(m_DeviceAddr);

            int handle = 0;

            Cursor.Current = Cursors.WaitCursor;

            int result = BSSDK.BS_OpenSocket(addr.ToString(), Int32.Parse(port.Text), ref handle);

            Cursor.Current = Cursors.Default;

            if (result != BSSDK.BS_SUCCESS)
            {
                MessageBox.Show("Cannot connect to the device", "Error");
                return;
            }

            m_MainForm.AddConnectedDevice(m_DeviceID, m_DeviceType, m_DeviceAddr, handle);
        }
    }
}