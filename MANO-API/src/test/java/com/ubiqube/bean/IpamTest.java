package com.ubiqube.bean;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.DynamicContainer.*;
import static org.junit.jupiter.api.DynamicTest.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.net.util.SubnetUtils;
import org.apache.commons.net.util.SubnetUtils.SubnetInfo;
import org.junit.jupiter.api.DynamicNode;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

import com.netcelo.commun.util.IpAddress;

public class IpamTest {
    
    @Test
    void testName() throws Exception {
        IpAddress baseIp = new IpAddress("100.64.96.0", "255.255.248.0");
        Set<IpAddress> usedIpAddresses = new HashSet<IpAddress>();
        for (int i=0; i< 210; i++) {
            String added = IpAddress.addressFromLong(baseIp.getLongAddress()+i);
            System.out.println(">>" + added);
            IpAddress ip = new IpAddress(added, "255.255.255.255");
            usedIpAddresses.add(ip);
        }
        usedIpAddresses.add(new IpAddress("100.64.96.208", "255.255.255.255"));
        usedIpAddresses.add(new IpAddress("100.64.96.208", "255.255.255.0"));
        System.out.println("" + getNextAvailableAddress("100.64.96.0", "255.255.254.0", usedIpAddresses) );
    }
    public static String getNextAvailableAddress(String subnet, String mask, Set<IpAddress> usedIpAddresses) {
        SubnetInfo subnetInfo = (new SubnetUtils(subnet, mask)).getInfo();
        String networkAddress = subnetInfo.getNetworkAddress();
        String broadcastAddress = subnetInfo.getBroadcastAddress();

        List<IpAddress> subnodes = getAddressesMatching(networkAddress, mask, usedIpAddresses);

        long addr = IpAddress.longvalueFromAddress(networkAddress);
        if (!StringUtils.equals(mask, "255.255.255.255") && !StringUtils.equals(mask, "255.255.255.254")) {
            addr += 1;
        }
        for (IpAddress subnode : subnodes) {
            if (subnode.getLongAddress() != addr) {
                return IpAddress.addressFromLong(addr);
            }
            addr++;
        }
        long net = IpAddress.longvalueFromAddress(networkAddress);
        long netMask = IpAddress.longvalueFromAddress(mask);
        String addressFromLong = IpAddress.addressFromLong(addr);

        if (StringUtils.equals(broadcastAddress, addressFromLong)) {
            return null;
        }

        if (net == (addr & netMask)) {
            addressFromLong = IpAddress.addressFromLong(addr);
            return addressFromLong;
        }
        return "";
    }
    
    private static List<IpAddress> getAddressesMatching(String networkAddress, String networkMask, Set<IpAddress> allNodes) {
        List<IpAddress> list = new ArrayList<IpAddress>();
        long addr = IpAddress.longvalueFromAddress(networkAddress);
        long mask = IpAddress.longvalueFromAddress(networkMask);
        for (IpAddress node : allNodes) {

            long entAddr = node.getLongAddress();

            if (addr == ((entAddr & mask) & 0xFFFFFFFF) && (addr != entAddr)) {
                list.add(node);
            }
        }
        Collections.sort(list);
        return list;
    }
}
